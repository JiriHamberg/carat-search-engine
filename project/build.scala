import sbt._
import Keys._
import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._
import com.earldouglas.xwp.JettyPlugin
import com.mojolly.scalate.ScalatePlugin._
import ScalateKeys._

object CarapAPIProtoBuild extends Build {
  val Organization = "Carat"
  val Name = "Carat API Prototype"
  val Version = "0.1.0-SNAPSHOT"
  val ScalaVersion = "2.10.4"
  val ScalatraVersion = "2.4.0.M2"

  lazy val project = Project (
    "CaratAPIPrototype",
    file("."),
    settings = ScalatraPlugin.scalatraSettings ++ scalateSettings ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      resolvers += Classpaths.typesafeReleases,
      resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases",
      libraryDependencies ++= Seq(
        "org.scala-lang" % "scala-reflect" % ScalaVersion,
        //"org.scala-lang" % "scalap" % ScalaVersion,
        "org.scalatra" %% "scalatra" % ScalatraVersion,
        "org.scalatra" %% "scalatra-scalate" % ScalatraVersion,
        "org.scalatra" %% "scalatra-specs2" % ScalatraVersion % "test",
        "ch.qos.logback" % "logback-classic" % "1.1.5" % "runtime",
        "org.eclipse.jetty" % "jetty-webapp" % "9.2.15.v20160210" % "container",
        "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
        //"org.scalatra" %% "scalatra-json" %  ScalatraVersion,
        //"org.json4s"   %% "json4s-jackson" % "3.3.0",
        //"org.json4s"   %% "json4s-jackson" % "3.5.0",
        "org.scalatra" %% "scalatra-json" % "2.3.0",
        "org.json4s"   %% "json4s-jackson" % "3.2.9",
        "org.json4s"  %% "json4s-native" % "3.2.9",
        "com.typesafe" % "config" % "1.3.1",
        "org.scalaj" % "scalaj-http_2.10" % "2.3.0",
        //web jars - frontend libraries
        "org.webjars" % "bootstrap" % "3.2.0",
        "org.webjars" % "jquery" % "3.2.0",
        "org.webjars.bower" % "mustache" % "2.3.0",
        "org.webjars" % "spin-js" % "2.0.0"
        //"org.webjars.npm" % "ejs" % "2.5.2"
      ),
      scalateTemplateConfig in Compile <<= (sourceDirectory in Compile){ base =>
        Seq(
          TemplateConfig(
            base / "webapp" / "WEB-INF" / "templates",
            Seq.empty,  /* default imports should be added here */
            Seq(
              Binding("context", "_root_.org.scalatra.scalate.ScalatraRenderContext", importMembers = true, isImplicit = true)
            ),  /* add extra bindings here */
            Some("templates")
          )
        )
      }
    )
  ).enablePlugins(JettyPlugin)
}
