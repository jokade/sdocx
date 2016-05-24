//     Project: sdocx
//      Module: word
// Description: configuration for document generation

// Copyright (c) 2016 Johannes Kastner <jokade@karchedon.de>
//                      Distributed under the MIT license.
package biz.enef.sdocx.word

case class WordConfig(version: WordFormatVersion.Value) {
  def isLegacy = version == WordFormatVersion.V2006
}

object WordConfig {
  implicit val default = WordConfig(WordFormatVersion.V2006)
}

object WordFormatVersion extends Enumeration {
  val V2008 = Value
  val V2006 = Value
}