//     Project: sdocx
//      Module: 
// Description: 

// Copyright (c) 2015 Johannes Kastner <jokade@karchedon.de>
//                      Distributed under the MIT license.
package biz.enef.sdocx.word

import java.io.{FileOutputStream, BufferedOutputStream, OutputStream}

import biz.enef.sdocx.opc._
import biz.enef.sdocx.opc.zip.ZipPackageWriter

object WordPackageWriter {

  private val contentTypes = ContentTypesProducer(
    Seq(ContentTypeDefault("rels","application/vnd.openxmlformats-package.relationships+xml")),
    Seq(ContentTypeOverride("/word/document.xml","application/vnd.openxmlformats-officedocument.wordprocessingml.document.main+xml"))
  )

  private val relationships = RelationshipsProducer("_rels/.rels",Seq(
    Relationship("rId1","http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument","/word/document.xml")
  ))

  def write(out: OutputStream, doc: WordDocument): Unit = {
    val parts = Seq(contentTypes,relationships,XmlPartProducer("word/document.xml",doc))
    ZipPackageWriter.writeParts(parts,out)
  }

  def write(file: String, doc: WordDocument): Unit = {
    val out = new BufferedOutputStream(new FileOutputStream(file))
    write(out,doc)
    out.close()
  }
}
