//     Project: sdocx
//      Module: word
// Description: Trait and generators for table properties

// Copyright (c) 2015 Johannes Kastner <jokade@karchedon.de>
//                      Distributed under the MIT license.
package biz.enef.sdocx.word.table

import biz.enef.sdocx.opc.XMLSerializable
import biz.enef.sdocx.word.XMLContent

/**
 * Table properties (i.e. contents of `<w:tblPr>`)
 */
trait TableProperty extends XMLSerializable


object TableProperty {
  /**
   * Sets the table width to the specified percentage of the total paragraph width.
   *
   * @param percent
   */
  def width(percent: Float) : TableProperty = {
    val width = Math.round(percent*50) + "%"
    XMLContent(<w:tblW w:w={width} type="pct"/>)
  }
  def style(name: String) : TableProperty = XMLContent(<w:tblStyle w:val={name} />)

  val fixedLayout: TableProperty = XMLContent(<w:tblLayout w:type="fixed" />)
}
