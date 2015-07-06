//     Project: sdocx
//      Module:
// Description:

// Copyright (c) 2015 Johannes Kastner <jokade@karchedon.de>
//                      Distributed under the MIT license.
package biz.enef.sdocx.word

import java.io.Writer

import biz.enef.sdocx.opc.XMLSerializable

case class WordDocument(content: WordBodyContent*) extends XMLSerializable {
  def write(w: Writer): Unit = {
    w.write(
      """<w:document xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main"
        |            xmlns:r="http://schemas.openxmlformats.org/officeDocument/2006/relationships">
        |<w:body>""".stripMargin)
    content.foreach(_.write(w))
    w.write("""</w:body></w:document>""")
  }
}

trait WordBodyContent extends XMLSerializable
