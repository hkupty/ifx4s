package ifx4s.backends

import sys.process._
import ifx4s.data.Image
import ifx4s.ConversionStrategy
import scala.concurrent.{Future, ExecutionContext}

object ImageMagick extends BaseBackend {

  //- Private
  private lazy val implementedFormats: List[Array[String]] = (
    Process("convert", Seq("-list", "format"))
    #| Process("sed", Seq("-r", "-n", "/(r|-)(w|-)/p"))
    #| Process("awk", Seq("{ print $1 \" \" $3 }"))
    lineStream_!
  ).toList.drop(1).map(_.split(" "))

  private def getConversionFormats(action: String) = implementedFormats.filter(_(1).contains(action)).map(_(0))

  private lazy val canConvertToFormats: List[String] = getConversionFormats("w")
  private lazy val canConvertFromFormats: List[String] = getConversionFormats("r")

  //- Public
  override def isAvailable: Boolean = implementedFormats.nonEmpty

  override def convertImage(image: Image, targetFileType: String)(implicit strategy: ConversionStrategy, ec: ExecutionContext): Future[Image] = Future {
    val outFile: Image = strategy.getOutputFile(image, targetFileType)

    outFile
  }

  override def canConvertTo(fileType: String): Boolean = canConvertToFormats.exists(_.equals(fileType.toUpperCase))
  override def canConvertFrom(fileType: String): Boolean = canConvertFromFormats.exists(_.equals(fileType.toUpperCase))

  override def canConvert(fromFileType: String, toFileType: String): Boolean = (
    canConvertTo(toFileType) && canConvertFrom(fromFileType)
  )
}
