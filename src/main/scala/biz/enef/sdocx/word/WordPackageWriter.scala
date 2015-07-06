//     Project: sdocx
//      Module: 
// Description: 

// Copyright (c) 2015 Johannes Kastner <jokade@karchedon.de>
//                      Distributed under the MIT license.
package biz.enef.sdocx.word

import java.io.{FileOutputStream, BufferedOutputStream, OutputStream}
import java.util.zip.ZipInputStream

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

  def write(out: OutputStream, doc: WordDocument): Unit =
    ZipPackageWriter.writeParts(Seq(contentTypes,relationships,XmlPartProducer("word/document.xml",doc)),out)


  def write(file: String, doc: WordDocument): Unit = {
    val out = new BufferedOutputStream(new FileOutputStream(file))
    write(out,doc)
    out.close()
  }

  /**
   * Updates the document.xml part in the word package represented by `in`.
   *
   * @param in ZIP input stream of the word package to be updated
   * @param out OutputStream to which the updated package is written
   * @param doc
   */
  def update(in: ZipInputStream, out: OutputStream, doc: WordDocument): Unit =
    ZipPackageWriter.updatePackage(Seq(XmlPartProducer("word/document.xml",doc)),in,out)


}
