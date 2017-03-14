
lazy val commonSettings = Seq(
  name := "play-adt-to-json",
  version := "1.0",
  scalaVersion in ThisBuild := "2.11.8",
  libraryDependencies ++= Seq(
    "org.julienrf" %% "play-json-derived-codecs" % "3.3",
    "org.joda" % "joda-convert" % "1.8.1",
    "org.scalatest" %% "scalatest" % "3.0.1" % "test"
  )
)

lazy val playAdtToJson = (project in file(".")).settings(commonSettings: _*)
