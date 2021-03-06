//     Project: sdocx
//      Module:
// Description:

// Copyright (c) 2015 Johannes Kastner <jokade@karchedon.de>
//                      Distributed under the MIT license.
package biz.enef.sdocx.opc

import java.io.Writer

import scala.xml.{XML, Node}

trait XMLSerializable {
  def write(w: Writer): Unit
}

object XMLSerializable {
  def apply(s: =>String): XMLSerializable = new Impl(s)
  class Impl(s: =>String) extends XMLSerializable {
    override def write(w: Writer): Unit = w.write(s)
  }
}

abstract class XMLNodeSerializable extends XMLSerializable {
  def toXML: Node
  override def write(w: Writer) = XML.write(w,toXML,"UTF-8",false,null)
}

object XMLNodeSerializable {
 def apply(node: Node): XMLNodeSerializable = Impl(node)

 case class Impl(toXML: Node) extends XMLNodeSerializable
}
