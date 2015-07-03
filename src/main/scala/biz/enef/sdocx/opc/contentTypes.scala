//     Project: sdocx
//      Module:
// Description:

// Copyright (c) 2015 Johannes Kastner <jokade@karchedon.de>
//                      Distributed under the MIT license.
package biz.enef.sdocx.opc

case class ContentTypeOverride(partName: String, contentType: String) extends XMLSerializable {
  def toXML = <Override PartName={partName} ContentType={contentType} />
}

case class ContentTypeDefault(extension: String, contentType: String) extends XMLSerializable {
  def toXML = <Default Extension={extension} ContentType={contentType} />
}

case class ContentTypesProducer(defaults: Iterable[ContentTypeDefault], overrides: Iterable[ContentTypeOverride])
  extends XmlPartProducer {
  def name = "[Content_Types].xml"
  lazy val node =
<Types xmlns="http://schemas.openxmlformats.org/package/2006/content-types">
  {defaults.map(_.toXML)}
  {overrides.map(_.toXML)}
</Types>
}
