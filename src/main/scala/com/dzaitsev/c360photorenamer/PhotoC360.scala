package com.dzaitsev.c360photorenamer

import java.io.File
import java.util.regex.Pattern

/**
 * Created by dmitriyzaitsev on 8/15/15.
 */
object PhotoC360 {
  /** C360_2015-07-25-07-40-28-323_org.jpg */
  val pattern = Pattern.compile("C360_(\\d{4})-(\\d{2})-(\\d{2})-(\\d{2})-(\\d{2})-(\\d{2})-(\\d{3})(_org)*.jpg")

  def isC360(photoName: String): Boolean = photoName.matches(pattern.pattern())

  def isC360(photo: File): Boolean = isC360(photo.getName)

  def format(photoName: String): String = {
    val matcher = pattern.matcher(photoName)
    matcher.matches
    val year = matcher.group(1)
    val month = matcher.group(2)
    val day = matcher.group(3)
    val hour = matcher.group(4)
    val minute = matcher.group(5)
    val second = matcher.group(6)
    val millis = matcher.group(7)
    val isOrg = matcher.group(8) != null
    s"$year$month${day}_$hour$minute$second${millis}_C360${if (isOrg) "_org" else ""}.jpg"
  }
}
