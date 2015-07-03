//     Project: sdocx
//      Module:
// Description:

// Copyright (c) 2015 Johannes Kastner <jokade@karchedon.de>
//                      Distributed under the MIT license.
package biz.enef.sdocx.word

import biz.enef.sdocx.opc.XMLNodeSerializable
import biz.enef.sdocx.word.table.TableXMLContent

import scala.xml.Node

object XMLContent {
  def apply(node: Node) = new Impl(node)

  case class Impl(toXML: Node) extends XMLNodeSerializable
    with WordBodyContent
    with TableXMLContent
}

