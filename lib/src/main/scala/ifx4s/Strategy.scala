package ifx4s

import ifx4s.data._
import sys.process._

abstract trait ConversionStrategy {
  def getOutputFilename(image: Image, fileType: String): String = image.filename.dropRight(image.filename.indexOf(".", 0) - 1).concat(fileType)
  def getOutputFile(image: Image, fileType: String): Image

}

object NamedPipeConversionStrategy extends ConversionStrategy {

  override def getOutputFile(image: Image, fileType: String) = {
    val filePath = super.getOutputFilename(image, fileType)
    val ret = Process("mkfifo", Seq(filePath))!!

    // TODO: Change to select dynamically the type
    NioImage(filePath, fileType)
  }

}
