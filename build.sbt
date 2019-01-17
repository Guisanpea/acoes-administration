name := """acoes-admin"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.6"

crossScalaVersions := Seq("2.11.12", "2.12.4")

libraryDependencies ++= Seq(
  javaJdbc,
  javaJpa,
  "org.mariadb.jdbc" % "mariadb-java-client" % "2.3.0",
  "org.hibernate" % "hibernate-core" % "5.3.6.Final",
  javaWs % "test",
  guice,
  "org.projectlombok" % "lombok" % "1.18.4" % "provided",
  "commons-beanutils" % "commons-beanutils" % "1.9.3"
)

PlayKeys.externalizeResources := false