//     Project: sdocx
//      Module:
// Description:

// Copyright (c) 2015 Johannes Kastner <jokade@karchedon.de>
//                      Distributed under the MIT license.
package biz.enef.sdocx.opc

import java.io.Writer

case class ContentTypeOverride(partName: String, contentType: String) extends XMLSerializable {
  final def write(w: Writer): Unit = {
    w.write(s"""<Override PartName="$partName" ContentType="$contentType"/>""")
  }
}

case class ContentTypeDefault(extension: String, contentType: String) extends XMLSerializable {
  final def write(w: Writer): Unit =
    w.write(s"""<Default Extension="$extension" ContentType="$contentType"/>""")
}

case class ContentTypesProducer(defaults: Iterable[ContentTypeDefault], overrides: Iterable[ContentTypeOverride])
  extends XmlPartProducer with XMLSerializable {
  def name = "[Content_Types].xml"
  def root = this
  final def write(w: Writer): Unit = {
    w.write(s"""<Types xmlns="http://schemas.openxmlformats.org/package/2006/content-types">""")
    defaults.foreach(_.write(w))
    overrides.foreach(_.write(w))
    w.write("</Types>")
  }
}
