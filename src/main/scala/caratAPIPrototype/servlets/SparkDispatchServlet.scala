
package caratAPIPrototype.servlets

import caratAPIPrototype.services.SparkDispatcher

import scala.util.Try
import scala.concurrent.{Future, ExecutionContext}
import scala.concurrent.duration._

import com.typesafe.config._

import org.scalatra._
import org.scalatra.FutureSupport

import org.json4s.{DefaultFormats, Formats}
import org.json4s.JsonAST.{JValue}
import org.scalatra.json._
import org.json4s.JsonDSL._

case class SparkJobOptions(
  minSupport: Option[Double],
  minConfidence: Option[Double],
  excluded: List[String]
)

class SparkDispatchServlet extends ScalatraServlet with JacksonJsonSupport with FutureSupport {
  protected implicit val jsonFormats: Formats = DefaultFormats
	implicit val executor =  ExecutionContext.global
  val conf = ConfigFactory.load()
	override val asyncTimeout = conf.getInt("spark-server.timeout") seconds

  before() {
    contentType = formats("json")
  }

	post("/") {
      val sparkJobOptions = parsedBody.extract[SparkJobOptions]
	    Future(SparkDispatcher.postRequest(sparkJobOptions))
  	}

}
