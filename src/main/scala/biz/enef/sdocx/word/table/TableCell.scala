//     Project: sdocx
//      Module:
// Description:

// Copyright (c) 2015 Johannes Kastner <jokade@karchedon.de>
//                      Distributed under the MIT license.
package biz.enef.sdocx.word.table

import biz.enef.sdocx.opc.XMLSerializable
import biz.enef.sdocx.word.XMLContent

trait TableCell extends XMLSerializable

object TableCell {
  def apply(text: String): TableCell = XMLContent(<w:tc><w:p><w:r><w:t>{text}</w:t></w:r></w:p></w:tc>)
}
