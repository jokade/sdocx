//     Project: sdocx
//      Module: E2E tests
// Description: Creates a simple docx

// Copyright (c) 2015 Johannes Kastner <jokade@karchedon.de>
//                    Distributed under the MIT license.
package tests

import biz.enef.sdocx.word.{XMLContent, WordPackageWriter, WordDocument}

object Test01 extends E2ETest {
  def name = "Test01"
  def run(): Unit = {
    val doc = WordDocument(XMLContent{
      <w:p><w:r><w:t>Hello World!</w:t></w:r></w:p>
    })
    WordPackageWriter.write("tests/Test01.docx",doc)
  }
}
