//     Project: sdocx
//      Module: word
// Description: Trait and generators for table properties

// Copyright (c) 2015 Johannes Kastner <jokade@karchedon.de>
//                      Distributed under the MIT license.
package biz.enef.sdocx.word.table

import java.io.Writer

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

  def cellMargins(top: Int = 0, start: Int = 0, bottom: Int = 0, end: Int = 0) : TableProperty = XMLContent(
    <w:tblCellMar>
      {if(top>0) <w:top w:w={top.toString} w:type="dxa"/>}
      {if(start>0) <w:start w:w={start.toString} w:type="dxa"/>}
      {if(bottom>0) <w:bottom w:w={bottom.toString} w:type="dxa"/>}
      {if(end>0) <w:end w:w={end.toString} w:type="dxa"/>}
    </w:tblCellMar>
  )

  def borders(borders: TableCellBorder*): TableProperty = TableBorders(borders)

  val fixedLayout: TableProperty = XMLContent(<w:tblLayout w:type="fixed" />)

  case class TableBorders(borders: Iterable[TableCellBorder]) extends TableProperty {
    override def write(w: Writer): Unit = {
      w.write("<w:tblBorders>")
      borders.foreach(_.write(w))
      w.write("</w:tblBorders>")
    }
  }
}
