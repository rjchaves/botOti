ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

val apiClient = "1.31.5"

resolvers += "google-api-services" at "https://google-api-client-libraries.appspot.com/mavenrepo"

lazy val root = (project in file("."))
  .settings(
    name := "botOti",
    idePackagePrefix := Some("es.rjchav")
  )

libraryDependencies += "com.discord4j" % "discord4j-core" % "3.2.2"
libraryDependencies += "org.jsoup" % "jsoup" % "1.14.3"
libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.12.0"
libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.5.13"
libraryDependencies += "com.google.code.gson" % "gson" % "2.9.0"


