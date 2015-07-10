//     Project: sdocx
//      Module:
// Description:

// Copyright (c) 2015 Johannes Kastner <jokade@karchedon.de>
//                      Distributed under the MIT license.
package biz.enef.sdocx.word.table

import java.io.Writer

import biz.enef.sdocx.opc.XMLSerializable
import biz.enef.sdocx.word.XMLContent

trait TableRow extends XMLSerializable

object TableRow {
  def apply(cells: TableCell*): TableRow = Impl(cells,Nil)

  def apply(cells: Seq[TableCell], props: TableRowProperty*): TableRow = Impl(cells,props)

  case class Impl(cells: Seq[TableCell], props: Seq[TableRowProperty]) extends TableRow {
    final def write(w: Writer): Unit = {
      w.write("<w:tr><w:trPr>")
      props.foreach(_.write(w))
      w.write("</w:trPr>")
      cells.foreach(_.write(w))
      w.write("</w:tr>")
    }
  }
}

trait TableRowProperty extends XMLSerializable

object TableRowProperty {
  val cantSplit: TableRowProperty = XMLContent(<w:cantSplit/>)
  val tblHeader: TableRowProperty = XMLContent(<w:tblHeader/>)
}


