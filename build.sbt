organization := "com.github.hkupty"

lazy val projectVersions = Seq(
  scalaVersion := "2.11.7",
  version := "0.0.1"
)

lazy val root = (project in file("lib")).
  settings(projectVersions).
  settings(
    name := "ifx4s"
  )

