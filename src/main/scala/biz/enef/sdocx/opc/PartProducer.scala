//     Project: sdocx
//      Module:
// Description:

// Copyright (c) 2015 Johannes Kastner <jokade@karchedon.de>
//                      Distributed under the MIT license.
package biz.enef.sdocx.opc

import java.io.OutputStream

trait PartProducer extends Part {
  def write(out: OutputStream) : Unit
}
