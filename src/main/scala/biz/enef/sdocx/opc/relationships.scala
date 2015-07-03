//     Project: sdocx
//      Module:
// Description:

// Copyright (c) 2015 Johannes Kastner <jokade@karchedon.de>
//                      Distributed under the MIT license.
package biz.enef.sdocx.opc

import scala.xml.Node

case class Relationship(id: String, tpe: String, target: String) extends XMLSerializable {
  def toXML = <Relationship Id={id} Type={tpe} Target={target} />
}

case class RelationshipsProducer(name: String, relationships: Iterable[Relationship]) extends XmlPartProducer {
  lazy val node: Node =
<Relationships xmlns="http://schemas.openxmlformats.org/package/2006/relationships">
  {relationships.map(_.toXML)}
</Relationships>
}
