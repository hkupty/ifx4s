organization := "com.github.hkupty"
lazy val deps = Seq(
  libraryDependencies ++= Seq(
    "com.github.pathikrit" %% "better-files" % "2.14.0"
  )
)


lazy val projectVersions = Seq(
  scalaVersion := "2.11.7",
  version := "0.0.1"
)

lazy val root = (project in file("lib")).
  settings(projectVersions).
  settings(deps).
  settings(
    name := "ifx4s"
  )

