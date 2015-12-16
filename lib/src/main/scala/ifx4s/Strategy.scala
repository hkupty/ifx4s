package ifx4s

import ifx4s.data._
import sys.process._

abstract trait Strategy {
  def getOutputFilename(image: Image, fileType: String): String = image.filename.dropRight(image.filename.indexOf(".", 0) - 1).concat(fileType)
  def getOutputFile(image: Image, fileType: String): Image
  def createImage(filePath: String, fileType: String): Image

}

trait NamedPipeStrategy extends Strategy {
  override def getOutputFile(image: Image, fileType: String): Image = {
    val filePath = super.getOutputFilename(image, fileType)
    val ret = Process("mkfifo", Seq(filePath))!!

    createImage(filePath, fileType)
  }
}

trait FilesImageStrategy extends Strategy {

  override def createImage(filePath: String, fileType: String): Image = FileImage(filePath, fileType)
}
