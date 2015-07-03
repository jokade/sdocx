//     Project: sdocx
//      Module:
// Description:

// Copyright (c) 2015 Johannes Kastner <jokade@karchedon.de>
//                      Distributed under the MIT license.
package biz.enef.sdocx.word.table

import biz.enef.sdocx.opc.XMLSerializable
import biz.enef.sdocx.word.XMLContent

trait TableProperty extends XMLSerializable

object TableProperty {
  def width(percent: Float) : TableProperty = {
    val width = Math.round(percent*50) + "%"
    XMLContent(<w:tblW w:w={width} type="pct"/>)
  }
  def style(name: String) : TableProperty = XMLContent(<w:tblStyle w:val={name} />)
}
