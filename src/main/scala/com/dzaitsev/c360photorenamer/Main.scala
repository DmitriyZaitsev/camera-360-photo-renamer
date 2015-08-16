package com.dzaitsev.c360photorenamer

import java.io.File

import scala.io.StdIn


/**
 * Created by dmitriyzaitsev on 8/15/15.
 */
object Main {

  def main(args: Array[String]) {
    val photosC360 = getFilesFrom(System.getProperty("user.dir")).filter(PhotoC360.is360)

    if (photosC360.nonEmpty) {
      println("Are you sure you want to rename following files?")
      println()
      photosC360.foreach(p => println(p.getName))
      println("(y/n)?")

      val answer = StdIn.readLine()

      if (answer.toLowerCase == "y") {
        photosC360.foreach(p => renamePhoto(p))
        print("Renaming finished!".toUpperCase)
      }
    } else println("There are no Camera 360 photos to rename!".toUpperCase)
  }

  def renamePhoto(p: File) {
    val maybePhoto = PhotoC360(p)

    if (maybePhoto.isDefined) {
      val photo360 = maybePhoto.get
      val newName = photo360.formatName

      if (p.renameTo(new File(p.getParentFile, newName))) {
        println(f"Rename $photo360%36s -> $newName")
      }
    }
  }

  def getFilesFrom(path: String): Array[File] = new File(path).listFiles().filter(_.isFile)
}
