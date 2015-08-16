package com.dzaitsev.c360photorenamer

import java.io.File
import java.util.regex.Pattern

/**
 * Created by dmitriyzaitsev on 8/15/15.
 */
class PhotoC360(val photoName: String) {
  val matcher = PhotoC360.pattern.matcher(photoName)
  matcher.matches
  val year = matcher.group(1)
  val month = matcher.group(2)
  val day = matcher.group(3)
  val hour = matcher.group(4)
  val minute = matcher.group(5)
  val second = matcher.group(6)
  val millis = matcher.group(7)
  val isOrg = matcher.group(8) != null

  override def toString = photoName

  def formatName = s"$year$month${day}_$hour$minute$second${millis}_C360${if (isOrg) "_org" else ""}.jpg"
}

object PhotoC360 {
  /** C360_2015-07-25-07-40-28-323_org.jpg */
  val pattern = Pattern.compile("C360_(\\d{4})-(\\d{2})-(\\d{2})-(\\d{2})-(\\d{2})-(\\d{2})-(\\d{3})(_org)*.jpg")

  def is360(photoName: String): Boolean = photoName.matches(pattern.pattern())

  def is360(photo: File): Boolean = is360(photo.getName)

  def apply(photo: File): Option[PhotoC360] = {
    val photoName = photo.getName
    if (is360(photoName)) Option(new PhotoC360(photoName)) else None
  }
}
