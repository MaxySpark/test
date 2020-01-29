name := "demoprojectnew"

version := "0.2"

lazy val generated = (project in file("generated")).enablePlugins(PlayScala).dependsOn(rapidplay)

lazy val root = (project in file(".")).enablePlugins(PlayScala)
  .dependsOn(generated, rapidplay).aggregate(generated, rapidplay)

lazy val rapidplay = project

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice )

libraryDependencies += "net.logstash.logback" % "logstash-logback-encoder" % "4.11"

val reactiveMongoVer = "0.15.1-play26"
libraryDependencies ++= Seq(
  "org.reactivemongo" %% "play2-reactivemongo" % reactiveMongoVer
)


libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.11"

libraryDependencies ++= Seq(
  "com.pauldijou" %% "jwt-play" % "0.16.0"
)

libraryDependencies += "com.typesafe.play" %% "play-mailer" % "6.0.1"
libraryDependencies += "com.typesafe.play" %% "play-mailer-guice" % "6.0.1"
libraryDependencies += "com.sendgrid" % "sendgrid-java" % "4.2.1"

libraryDependencies += "com.google.cloud" % "google-cloud-language" % "1.51.0"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.2.3",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.3"
)