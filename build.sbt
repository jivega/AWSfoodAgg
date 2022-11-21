import Dependencies._

ThisBuild / scalaVersion     := "2.13.4"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.jivega.awsfoodagg"
ThisBuild / organizationName := "awsfoodagg"

lazy val root = (project in file("."))
  .settings(
    name := "AWSfoodAgg",
    libraryDependencies += scalaTest % Test
  )
