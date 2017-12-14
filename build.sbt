import sbt._
import Dependencies._
import BuildConstants._


lazy val commonSettings: Seq[Def.SettingsDefinition] = Seq(
  organization := org,
  scalaVersion := scalaVer,
  version := buildVer,
  libraryDependencies ++= Seq(scalaTest,scalacheck),
  fork := true
)

// ------------------------------------------------------
// common
lazy val common = (project in file("common/")).
  settings(commonSettings: _*).
  settings(
    name := "common",
  )


// ------------------------------------------------------
// labs

lazy val labs = (project in file("labs/")).
  enablePlugins(ProtobufPlugin).
  settings(commonSettings: _*).
  settings(
    name := "labs",
    javaSource in ProtobufConfig := ((sourceDirectory in Compile).value / "generated"),
  ).dependsOn(common)

// END labs ----------------------------------------------


// ------------------------------------------------------
// main project
lazy val course = (project in file(".")).
  settings(commonSettings: _*).
  settings(name := "course").aggregate(common,labs)
