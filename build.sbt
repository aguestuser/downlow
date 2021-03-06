name := "downlow"

version := "1.0"

lazy val `downlow` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq( jdbc , anorm , cache , ws )

libraryDependencies ++= Seq("org.postgresql" % "postgresql" % "9.4-1200-jdbc41")

libraryDependencies ++= Seq("org.scalaz" %% "scalaz-core" % "7.0.6")

libraryDependencies ~= { _.map(_.exclude("org.slf4j", "slf4j-simple")) }

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  