name := "domain"
 
version := "1.0"

libraryDependencies ++= Seq(
  "org.mariadb.jdbc" % "mariadb-java-client" % "2.3.0",
  "org.hibernate" % "hibernate-core" % "5.3.6.Final",
  "org.projectlombok" % "lombok" % "1.18.4" % "provided",
  "org.hibernate.validator" % "hibernate-validator" % "6.0.14.Final",
)

crossPaths := false
autoScalaLibrary := false
