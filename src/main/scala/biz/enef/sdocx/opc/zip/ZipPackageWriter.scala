//     Project: sdocx
//      Module:
// Description:

// Copyright (c) 2015 Johannes Kastner <jokade@karchedon.de>
//                      Distributed under the MIT license.
package biz.enef.sdocx.opc.zip

import java.io.OutputStream
import java.util.zip.{ZipEntry, ZipOutputStream}

import biz.enef.sdocx.opc.PartProducer

object ZipPackageWriter {

  def writeParts(parts: Iterable[PartProducer], out: OutputStream): Unit = {
    val zip = new ZipOutputStream(out)
    parts.foreach{ p =>
      zip.putNextEntry(new ZipEntry(p.name))
      p.write(zip)
      zip.closeEntry()
    }
    zip.close()
  }

  /*
  private def ensureDirectory(path: Seq[String], dirs: Set[Seq[String]], zip: ZipOutputStream) : Set[Seq[String]] =
    if(dirs.contains(path)) dirs
    else {
      path.foldLeft(""){ (prefix,rest) =>
        val p = prefix+rest.head+"/"
        zip.putNextEntry(new ZipEntry(p))
        zip.closeEntry()
        p
      }
      dirs + path
    }
    */
}
