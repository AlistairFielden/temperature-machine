
name := "temperature-machine"

scalaVersion := "2.11.7"

mainClass in Compile := Some("bad.robot.temperature.Main")

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.1.0",
  "org.rrd4j" % "rrd4j" % "2.2.1",
  "org.specs2" %% "specs2-core" % "3.6.6" % "test"
)

scalacOptions := Seq("-Xlint", "-Xfatal-warnings", "-deprecation", "-feature", "-language:implicitConversions,reflectiveCalls,higherKinds")
