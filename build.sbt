name := "finch-sample"

version := "0.0.0"

scalaVersion := "2.11.7"

resolvers ++= Seq(
  DefaultMavenRepository,
  Resolver.sonatypeRepo("snapshots"),
  "twitter repo" at "http://maven.twttr.com"
)

libraryDependencies ++= Seq(
  "io.getquill" %% "quill-finagle-mysql" % "0.5.1-SNAPSHOT",
  "com.github.finagle" %% "finch-core" % "0.10.0",
  "com.github.finagle" %% "finch-circe" % "0.10.0",
  "com.twitter" %% "twitter-server" % "1.19.0",
  "io.circe" %% "circe-generic" % "0.3.0",
  "com.typesafe" % "config" % "1.3.0",
  "org.flywaydb" % "flyway-core" % "4.0",
  "mysql" % "mysql-connector-java" % "5.1.38"
)
