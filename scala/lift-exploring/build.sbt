name := "lift-exploring"

version := "1.0"

scalaVersion := "2.11.11"

scalaBinaryVersion := "2.11"

val jettyVersion = "9.4.4.v20170414"

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-xml"              % "1.0.3",
  "net.liftweb"            %% "lift-webkit"            % "2.6.3",
  "net.liftweb"            %% "lift-mapper"            % "2.6.3",
  "net.liftmodules"        %% "lift-jquery-module_2.6" % "2.10",
  "org.eclipse.jetty"       % "jetty-webapp"           % jettyVersion,
  "org.eclipse.jetty"       % "jetty-plus"             % jettyVersion,
  "org.eclipse.jetty"       % "jetty-servlets"         % jettyVersion,
  "org.typelevel" %% "cats" % "0.9.0",
  "org.scalatest"          %% "scalatest"              % "3.0.1"       % "test"
)

scalacOptions ++= Seq(
    "-language:_",
    "-Xfatal-warnings",
    "-deprecation",
    "-encoding", "UTF-8",
    "-feature",
    "-unchecked",
    "-Xlint",
    "-Yno-adapted-args",
    "-Ywarn-dead-code",
    "-Ywarn-numeric-widen",
    //"-Ywarn-value-discard",
    "-Xfuture"
    //"-Xlog-implicits"
)

enablePlugins(JettyPlugin)
