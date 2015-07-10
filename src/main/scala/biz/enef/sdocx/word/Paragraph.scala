//     Project: sdocx
//      Module:
// Description:

// Copyright (c) 2015 Johannes Kastner <jokade@karchedon.de>
//                      Distributed under the MIT license.
package biz.enef.sdocx.word

import java.io.Writer

import biz.enef.sdocx.opc.XMLSerializable
import biz.enef.sdocx.Utils._

trait Paragraph extends WordBodyContent

object Paragraph {
  def apply(run: Run, properties: ParagraphProperty*): Paragraph = Impl(properties,Seq(run))

  def apply(runs: Iterable[Run], properties: ParagraphProperty*): Paragraph = Impl(properties,runs)

  def apply(text: String, properties: ParagraphProperty*): Paragraph = apply(Run(text),properties:_*)

  val empty = Paragraph(Run(""))

  case class Impl(properties: Iterable[ParagraphProperty], runs: Iterable[Run]) extends Paragraph {
    override def write(w: Writer): Unit = {
      w.write("<w:p><w:pPr>")
      properties.foreach(_.write(w))
      w.write("</w:pPr>")
      runs.foreach(_.write(w))
      w.write("</w:p>")
    }
  }
}

trait ParagraphProperty extends XMLSerializable

trait Run extends XMLSerializable

object Run {
  def apply(text: String, properties: RunProperty*): Run = Impl(text,properties)

  case class Impl(text: String, properties: Iterable[RunProperty]) extends Run {
    override def write(w: Writer): Unit = {
      val withBreaks = text.entitiesEncoded.replaceAll("""\n""","<w:br/>")
      w.write("<w:r><w:rPr>")
      properties.foreach( _.write(w) )
      w.write("</w:rPr><w:t>")
      w.write(withBreaks)
      w.write("</w:t></w:r>")
    }
  }
}

trait RunProperty extends XMLSerializable

object RunProperty {
  val bold: RunProperty = new Impl("<w:b/>")
  val italics: RunProperty = new Impl("<w:i/>")
  def fontSize(sz: Int) = new Impl(s"""<w:sz w:val="$sz"/>""")
  def color(value: String): RunProperty = Color(value)

  case class Color(value: String) extends RunProperty {
    override def write(w: Writer): Unit = w.write(s"""<w:color w:val="$value"/>""")
  }
  class Impl(s: =>String) extends XMLSerializable.Impl(s) with RunProperty
}
