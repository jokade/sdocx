//     Project: sdocx
//      Module:
// Description:

// Copyright (c) 2015 Johannes Kastner <jokade@karchedon.de>
//                      Distributed under the MIT license.
package biz.enef.sdocx.word.table

import biz.enef.sdocx.opc.XMLSerializable
import biz.enef.sdocx.word.XMLContent

trait TableColumn extends XMLSerializable

object TableColumn {
  def apply(width: Int): TableColumn = XMLContent( <w:gridCol w:w={width.toString}/> )
  def apply(percent: Float): TableColumn = {
    val width = Math.round(percent*102.96).toString
    XMLContent( <w:gridCol w:w={width}/> )
  }
  def apply(): TableColumn = XMLContent( <w:gridCol /> )
}