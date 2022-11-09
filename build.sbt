ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "CompilerWithScala",
    libraryDependencies += "com.lihaoyi" %% "fastparse" % "2.3.3"
  )
