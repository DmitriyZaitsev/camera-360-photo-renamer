package com.dzaitsev.c360photorenamer

import java.io.File

import scala.io.StdIn


/**
 * Created by dmitriyzaitsev on 8/15/15.
 */
object Main {

  def main(args: Array[String]) {
    val photosC360 = getFilesFrom(System.getProperty("user.dir")).filter(PhotoC360.isC360)

    if (photosC360.nonEmpty) {
      println("Are you sure you want to rename following files?")
      println()
      photosC360.foreach(photo => println(photo.getName))
      println("(y/n)?")

      val answer = StdIn.readLine()

      if (answer.toLowerCase == "y") {
        photosC360.foreach(rename)
        print("Renaming finished!".toUpperCase)
      }
    } else println("There are no Camera 360 photos to rename!".toUpperCase)
  }

  def rename(file: File) {
    val oldName = file.getName
    val newName = PhotoC360.format(oldName)

    if (file.renameTo(new File(file.getParentFile, newName))) {
      println(f"Rename $oldName%36s -> $newName")
    }
  }

  def getFilesFrom(path: String): Array[File] = new File(path).listFiles().filter(_.isFile)
}
