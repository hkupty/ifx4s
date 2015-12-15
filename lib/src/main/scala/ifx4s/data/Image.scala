package ifx4s.data

import java.nio.file.{Paths, Path, Files};
import scala.concurrent.{Future, ExecutionContext};


abstract trait Image {
  def filename: String
  def getBytes: Array[Byte]
  def getBytesAsync(implicit ec: ExecutionContext): Future[Array[Byte]]
}

case class NioImage(filename: String, fileType: String) extends Image {
  private val path: Path = Paths.get(filename)

  override def getBytes: Array[Byte] = Files.readAllBytes(path)
  override def getBytesAsync(implicit ec: ExecutionContext): Future[Array[Byte]] = Future(this.getBytes)
}
