
lazy val commonSettings = Seq(
  organization := "biz.enef",
  version := "0.1-SNAPSHOT",
  scalaVersion := "2.11.6",
  scalacOptions ++= Seq("-deprecation","-unchecked","-feature","-Xlint"),
  libraryDependencies ++= Seq(
    "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.3",
    "com.lihaoyi" %% "utest" % "0.3.1"
  ),
  testFrameworks += new TestFramework("utest.runner.Framework")
)


lazy val sdocx = project.in(file(".")).
  settings(commonSettings: _*).
  settings(publishingSettings: _*).
  settings( 
    name := "sdocx",
    libraryDependencies ++= Seq(
    ),
    resolvers += Resolver.sonatypeRepo("releases")
  )


lazy val tests = project
  .dependsOn(sdocx)
  .settings(commonSettings: _*)
  .settings(
    publishLocal := {},
    publish := {}
  )


lazy val publishingSettings = Seq(
  publishMavenStyle := true,
  publishTo := {
    val nexus = "https://oss.sonatype.org/"
    if (isSnapshot.value)
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases"  at nexus + "service/local/staging/deploy/maven2")
  },
  pomExtra := (
    <url>https://github.com/jokade/sdocx</url>
    <licenses>
      <license>
        <name>MIT License</name>
        <url>http://www.opensource.org/licenses/mit-license.php</url>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:jokade/sdocx</url>
      <connection>scm:git:git@github.com:jokade/sdocx.git</connection>
    </scm>
    <developers>
      <developer>
        <id>jokade</id>
        <name>Johannes Kastner</name>
        <email>jokade@karchedon.de</email>
      </developer>
    </developers>
  )
)
 
