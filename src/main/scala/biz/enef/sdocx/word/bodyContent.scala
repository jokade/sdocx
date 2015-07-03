//     Project: sdocx
//      Module:
// Description:

// Copyright (c) 2015 Johannes Kastner <jokade@karchedon.de>
//                      Distributed under the MIT license.
package biz.enef.sdocx.word

import biz.enef.sdocx.opc.XMLSerializable

sealed trait WordBodyContent extends XMLSerializable

case class Paragraph(text: String) extends WordBodyContent {
  def toXML = <w:p><w:r><w:t>{text}</w:t></w:r></w:p>
}
