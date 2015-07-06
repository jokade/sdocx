//     Project: sdocx
//      Module:
// Description:

// Copyright (c) 2015 Johannes Kastner <jokade@karchedon.de>
//                      Distributed under the MIT license.
package biz.enef.sdocx.word.table

import java.io.Writer

import biz.enef.sdocx.opc.XMLSerializable
import biz.enef.sdocx.word.{WordBodyContent, XMLContent}

trait TableCell extends XMLSerializable

object TableCell {
  def apply(text: String, properties: TableCellProperty*): TableCell = apply(
    XMLContent(<w:p><w:r><w:t>{text}</w:t></w:r></w:p>),
    properties:_*
  )
  def apply(content: WordBodyContent, properties: TableCellProperty*): TableCell =
    Impl( Seq(content), properties )

  def apply(content: Iterable[WordBodyContent], properties: TableCellProperty*): TableCell =
    Impl(content,properties)

  case class Impl(content: Iterable[WordBodyContent], properties: Iterable[TableCellProperty]) extends TableCell {
    override def write(w: Writer): Unit = {
      w.write("<w:tc><w:tcPr>")
      properties.foreach( _.write(w) )
      w.write("</w:tcPr>")
      content.foreach(_.write(w))
      w.write("</w:tc>")
    }
  }
}


trait TableCellProperty extends XMLSerializable

object TableCellProperty {
  def gridSpan(cols: Int): TableCellProperty = XMLContent(<w:gridSpan w:val={cols.toString}></w:gridSpan>)
  def borders(borders: TableCellBorder*): TableCellProperty = TableCellBorders(borders)

  case class TableCellBorders(borders: Iterable[TableCellBorder]) extends TableCellProperty {
    override def write(w: Writer): Unit = {
      w.write("<w:tcBorders>")
      borders.foreach(_.write(w))
      w.write("</w:tcBorders>")
    }
  }
}


trait TableCellBorder extends XMLSerializable

object TableCellBorder {
  def apply(pos: String, color: String = "", shadow: Boolean = false, space: Int = 0, sz: Int = 8, value: String = "single") : TableCellBorder =
    Impl(pos,color,shadow,space,sz,value)
  def top(color: String = "auto", shadow: Boolean = false, space: Int = 0, sz: Int = 8, value: String = "single") : TableCellBorder =
    Impl("top",color,shadow,space,sz,value)
  def bottom(color: String = "auto", shadow: Boolean = false, space: Int = 0, sz: Int = 8, value: String = "single") : TableCellBorder =
    Impl("bottom",color,shadow,space,sz,value)
  def start(color: String = "auto", shadow: Boolean = false, space: Int = 0, sz: Int = 8, value: String = "single") : TableCellBorder =
    Impl("start",color,shadow,space,sz,value)
  def end(color: String = "auto", shadow: Boolean = false, space: Int = 0, sz: Int = 8, value: String = "single") : TableCellBorder =
    Impl("end",color,shadow,space,sz,value)
  def insideH(color: String = "auto", shadow: Boolean = false, space: Int = 0, sz: Int = 8, value: String = "single") : TableCellBorder =
    Impl("insideH",color,shadow,space,sz,value)
  def insideV(color: String = "auto", shadow: Boolean = false, space: Int = 0, sz: Int = 8, value: String = "single") : TableCellBorder =
    Impl("insideV",color,shadow,space,sz,value)
  def tl2br(color: String = "auto", shadow: Boolean = false, space: Int = 0, sz: Int = 8, value: String = "single") : TableCellBorder =
    Impl("tl2br",color,shadow,space,sz,value)
  def tl2bl(color: String = "auto", shadow: Boolean = false, space: Int = 0, sz: Int = 8, value: String = "single") : TableCellBorder =
    Impl("tl2bl",color,shadow,space,sz,value)

  case class Impl(pos: String, color: String, shadow: Boolean, space: Int, sz: Int, value: String) extends TableCellBorder {
    override def write(w: Writer): Unit =
      w.write(s"""<w:$pos w:color="$color" w:shadow="$shadow" w:space="$space" w:sz="$sz" w:val="$value"/>""")
  }
}
