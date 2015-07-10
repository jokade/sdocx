//     Project: sdocx
//      Module:
// Description:

// Copyright (c) 2015 Johannes Kastner <jokade@karchedon.de>
//                      Distributed under the MIT license.
package biz.enef.sdocx

object Utils {

  implicit class RichString(val s: String) extends AnyVal {
    def entitiesEncoded: String = encodeEntites(s)
  }

  def encodeEntites(s: String) : String =
    s.replaceAll("&","&amp;")
      .replaceAll("\"","&quot;")
      .replaceAll("'","&apos;")
      .replaceAll("<","&lt;")
      .replaceAll(">","&gt;")

  /**
   * Converts the specified value from centimeters to twentieth of a point (1/1440 inch).
   *
   * @param cm value in centimeters
   */
  def cmToDxa(cm: Double): Int = Math.round(cm/2.54*1440).toInt
}
