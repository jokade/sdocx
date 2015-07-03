//     Project: sdocx
//      Module:
// Description:

// Copyright (c) 2015 Johannes Kastner <jokade@karchedon.de>
//                      Distributed under the MIT license.
package biz.enef.sdocx.opc

import java.io.Writer

case class Relationship(id: String, tpe: String, target: String) extends XMLSerializable {
  final def write(w: Writer): Unit =
    w.write(s"""<Relationship Id="$id" Type="$tpe" Target="$target"/>""")
}

case class RelationshipsProducer(name: String, relationships: Iterable[Relationship]) extends XmlPartProducer
  with XMLSerializable {
  def root = this
  final def write(w: Writer): Unit = {
    w.write("""<Relationships xmlns="http://schemas.openxmlformats.org/package/2006/relationships">""")
    relationships.foreach(_.write(w))
    w.write("</Relationships>")
  }
}
