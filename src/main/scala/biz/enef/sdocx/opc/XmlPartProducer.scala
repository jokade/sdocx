//     Project: sdocx
//      Module:
// Description:

// Copyright (c) 2015 Johannes Kastner <jokade@karchedon.de>
//                      Distributed under the MIT license.
package biz.enef.sdocx.opc

import java.io.{OutputStream, OutputStreamWriter}

import scala.xml.dtd.DocType
import scala.xml.{XML, Node}

abstract trait XmlPartProducer extends PartProducer {

  def node: Node

  override def write(out: OutputStream): Unit = {
    val w = new OutputStreamWriter(out)
    XML.write(w,node,"UTF-8",true,null) //XmlPartProducer.docType)
    w.flush()
  }
}

object XmlPartProducer {
  val docType = DocType("xml")

  def apply(name: String, node: Node): XmlPartProducer = new Impl(name,node)
  def apply(name: String, node: XMLSerializable): XmlPartProducer = new Impl(name,node.toXML)

  class Impl(val name: String, nodeProducer: =>Node) extends XmlPartProducer {
    def node = nodeProducer
  }
}

trait XMLSerializable {
  def toXML: Node
}
