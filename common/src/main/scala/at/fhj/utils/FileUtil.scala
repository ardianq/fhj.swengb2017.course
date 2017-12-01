package at.fhj.utils

import java.awt.image.BufferedImage
import java.io._
import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.{FileVisitResult, FileVisitor, Files, Path}
import javax.imageio.ImageIO

import scala.util.Try
import scala.util.control.NonFatal

object FileUtil extends CanLog {
  
  val deletionVisitor: FileVisitor[Path] = new FileVisitor[Path] {

    override def postVisitDirectory(dir: Path, exc: IOException): FileVisitResult = {
      Files.delete(dir)
      logTrace(s"Deleting ${dir.toAbsolutePath} ... ")
      FileVisitResult.CONTINUE
    }

    override def visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult = {
      Files.delete(file)
      FileVisitResult.CONTINUE
    }

    override def visitFileFailed(file: Path, exc: IOException): FileVisitResult = {
      FileVisitResult.CONTINUE
    }

    override def preVisitDirectory(dir: Path, attrs: BasicFileAttributes) = FileVisitResult.CONTINUE
  }

  /**
    * Recursively deletes a directory and its descendants.
    *
    * @param targetPath
    */
  def deleteDirectory(targetPath: Path): Unit = {
    Files.walkFileTree(targetPath, deletionVisitor)
  }

  def deleteFile(p : Path)   : Unit ={
    Files.delete(p)
    logTrace(s"Deleting ${p.toAbsolutePath} ... ")
  }

  /**
    * Writes content to a file and ensures it gets _really_ written.
    *
    * @param path    the path to write to
    * @param content the content of the target file
    */
  private def toPath(path: Path, content: ByteBuffer): Try[Unit] = Try {
    time({
      val fous = new FileOutputStream(path.toFile)
      try {
        val c = fous.getChannel
        c.write(content)
        c.force(true)
        fous.flush()
        fous.getFD.sync()
      } finally {
        fous.close()
      }
    }, t => logTrace(s"Wrote file ${path.toAbsolutePath.toString} in $t ms."))
  }

  def toPath(path: Path, content: String): Try[Unit] = {
    toPath(path, ByteBuffer.wrap(content.getBytes(StandardCharsets.UTF_8)))
  }

  def toPath(path: Path, content: Array[Byte]): Try[Unit] =
    toPath(path, ByteBuffer.wrap(content))

  def toPath(path: Path, content: List[Array[Byte]]): Try[Unit] =
    toPath(path, ByteBuffer.wrap(content.flatten.toArray))


  /**
    * Creates a .bmp file on disc (and parent directories if needed) for the given buffered image.
    *
    * @param path  the path which denotes the image
    * @param image the image to persist
    * @return
    */
  def toPath(path: Path, image: BufferedImage): Boolean = {
    time({
      ImageIO.write(image, "bmp", path.toFile)
    }, t => logTrace(s"Wrote image ${path.toAbsolutePath} in $t ms."))
  }

  def printToFile(f: File)(op: BufferedWriter => Unit, withBOM: Boolean = false): Unit = {
    val fos = new FileOutputStream(f)

    if (withBOM) {
      fos.write(Array[Byte](0xEF.toByte, 0xBB.toByte, 0xBF.toByte))
    }
    val osw = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"))
    try {
      op(osw)
      ()
    } catch {
      case NonFatal(e) =>
        logException(e.getMessage, e)
        sys.error(e.getMessage)
    } finally {
      if (osw != null) {
        osw.flush()
        osw.close()
        if (fos != null) {
          fos.close()
        }
      }
    }
  }

}
