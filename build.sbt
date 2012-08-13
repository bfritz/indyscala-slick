name := "IndyScala SLICK examples"

version := "0.0.1"

organization := "org.indyscala"

scalaVersion := "2.10.0-M6"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= List(
  "com.typesafe" % "slick_2.10.0-M6" % "0.11.0",
  "org.slf4j" % "slf4j-simple" % "1.6.4",
  "com.h2database" % "h2" % "1.3.166",
  "postgresql" % "postgresql" % "9.1-901.jdbc4",
  "mysql" % "mysql-connector-java" % "5.1.21"
)
