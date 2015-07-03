//     Project: sdocx
//      Module:
// Description:

// Copyright (c) 2015 Johannes Kastner <jokade@karchedon.de>
//                      Distributed under the MIT license.
package biz.enef.sdocx.word

import biz.enef.sdocx.opc.XMLSerializable

case class WordDocument(content: WordBodyContent*) extends XMLSerializable {
  def toXML =
<w:document xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main">
  <w:body>
    {content.map(_.toXML)}
  </w:body>
</w:document>
}

