//     Project: sdocx
//      Module:
// Description:

// Copyright (c) 2015 Johannes Kastner <jokade@karchedon.de>
//                      Distributed under the MIT license.
package biz.enef.sdocx.opc.zip

import java.io.OutputStream
import java.util.zip.{ZipInputStream, ZipEntry, ZipOutputStream}

import biz.enef.sdocx.opc.PartProducer
import slogging.LazyLogging

object ZipPackageWriter extends LazyLogging {
  val BUFSIZE = 4096

  def writeParts(parts: Iterable[PartProducer], out: OutputStream): Unit = {
    val zip = new ZipOutputStream(out)
    parts.foreach{ p =>
      zip.putNextEntry(new ZipEntry(p.name))
      p.write(zip)
      zip.closeEntry()
    }
    zip.close()
  }


  def updatePackage(parts: Iterable[PartProducer], in: ZipInputStream, out: OutputStream): Unit = {
    logger.trace(s"Updating OPC zip package")
    var todo = parts.map(p => (p.name,p) ).toMap
    val zip = new ZipOutputStream(out)
    val buf = new Array[Byte](BUFSIZE)
    @annotation.tailrec
    def loop(in: ZipInputStream): Unit = in.getNextEntry match {
      case null => ()
      case e if todo.contains(e.getName) =>
        val name = e.getName
        logger.trace(s"  updating entry '$name'")
        zip.putNextEntry(new ZipEntry(name))
        todo(name).write(zip)
        zip.closeEntry()
        todo -= name
        loop(in)
      case e =>
        logger.trace(s"  copying entry '${e.getName}'")
        zip.putNextEntry(new ZipEntry(e.getName))
        var nread = in.read(buf,0,BUFSIZE)
        while(nread > -1) {
          zip.write(buf,0,nread)
          nread = in.read(buf,0,BUFSIZE)
        }
        zip.closeEntry()
        loop(in)
    }

    // copy/update exisiting entries
    loop(in)

    // add new entries
    todo.foreach{ p =>
      logger.trace(s"  adding new entry '${p._1}'")
      zip.putNextEntry(new ZipEntry(p._1))
      p._2.write(zip)
      zip.closeEntry()
    }

    zip.close()
  }
}
