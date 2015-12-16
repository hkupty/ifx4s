package ifx4s.data

import better.files._


abstract trait Image {
  def filename: String
  def getBytes: Iterator[Byte]
}

case class FileImage(filename: String, fileType: String) extends Image {
  private val path: File = filename.toFile

  override def getBytes: Iterator[Byte] = path.bytes
}
