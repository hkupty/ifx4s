package ifx4s.backends

import ifx4s.Strategy
import ifx4s.data.Image
import scala.concurrent.{Future, ExecutionContext}


trait BaseBackend {
  def isAvailable: Boolean

  def convertImage(image: Image, targetFileType: String)(implicit strategy: Strategy, ec: ExecutionContext): Future[Image]

  def canConvertTo(fileType: String): Boolean
  def canConvertFrom(fileType: String): Boolean
  def canConvert(fromFileType: String, toFileType: String): Boolean
}
