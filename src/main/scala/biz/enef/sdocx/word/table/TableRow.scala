//     Project: sdocx
//      Module:
// Description:

// Copyright (c) 2015 Johannes Kastner <jokade@karchedon.de>
//                      Distributed under the MIT license.
package biz.enef.sdocx.word.table

import java.io.Writer

import biz.enef.sdocx.opc.XMLSerializable

trait TableRow extends XMLSerializable

object TableRow {
  def apply(cells: TableCell*): TableRow = Impl(cells)

  case class Impl(cells: Seq[TableCell]) extends TableRow {
    final def write(w: Writer): Unit = {
      w.write("<w:tr>")
      cells.foreach(_.write(w))
      w.write("</w:tr>")
    }
  }
}
