//     Project: sdocx
//      Module:
// Description:

// Copyright (c) 2015 Johannes Kastner <jokade@karchedon.de>
//                      Distributed under the MIT license.
package biz.enef.sdocx.opc

import java.io.{PrintWriter, OutputStream, OutputStreamWriter}

import scala.xml.Node

abstract class XmlPartProducer extends PartProducer {
  def root: XMLSerializable
  override def write(out: OutputStream): Unit = {
    val w = new OutputStreamWriter(out)
    w.write( """<?xml version="1.0" encoding="UTF-8"?>"""+'\n')
    root.write(w)
    w.flush()
  }
}


object XmlPartProducer {

  def apply(name: String, node: Node): XmlPartProducer = new Impl(name,XMLNodeSerializable(node))
  def apply(name: String, root: XMLSerializable): XmlPartProducer = new Impl(name,root)

  case class Impl(name: String, root: XMLSerializable) extends XmlPartProducer
}



