package at.fhj.utils

import java.nio.file.{Files, Path}

case class Csv(path: Path) {

  import scala.collection.JavaConverters._

  lazy val entries: Map[String, String] = {
    val lines: Seq[String] = Files.readAllLines(path).asScala
    val seqOfTuples: Seq[(String, String)] =
      for {line <- lines
           arr = line.split(",")} yield {
        arr match {
          case Array(a, b) if p(a) && p(b) =>
            val id = r(a)
            val github = r(b)
            id -> github
          case _ => ???
        }
      }
    seqOfTuples.toMap
  }

  // precondition: entries must be enclosed by '"'
  def p(s: String): Boolean = s.startsWith("\"") && s.endsWith("\"")

  // replace strings if necessary
  def r(s: String): String = if (p(s)) s.substring(1, s.length - 1) else s


}
