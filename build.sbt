import sbt._
import Dependencies._
import BuildConstants._


// ------------------------------------------------------
// common
lazy val common = (project in file("common/")).
  settings(
    organization := org,
    scalaVersion := scalaVer,
    version := buildVer,
    name := "common",
    libraryDependencies += scalaTest,
    fork := true
  )


// ------------------------------------------------------
// labs

lazy val labs = (project in file("labs/")).
 settings(
    organization := org,
    scalaVersion := scalaVer,
    version := buildVer,
    name := "labs",
    libraryDependencies += scalaTest,
    fork := true
  ).dependsOn(common)

// END labs ----------------------------------------------


// ------------------------------------------------------
// main project
lazy val course = (project in file(".")).
  settings(
    organization := org,
    scalaVersion := scalaVer,
    version := buildVer,
    name := "course",
    libraryDependencies += scalaTest
  ).aggregate(common,labs)
