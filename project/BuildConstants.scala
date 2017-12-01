import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.3"
  lazy val jgit = "org.eclipse.jgit" % "org.eclipse.jgit" % "4.9.0.201710071750-r"
}

object BuildConstants {
  val org: String = "at.fh-joanneum.ima"
  val buildVer = "2017.0.4-SNAPSHOT"
  val scalaVer = "2.12.3"
}
