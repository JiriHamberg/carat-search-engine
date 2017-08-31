
package caratAPIPrototype.servlets

import caratAPIPrototype.services.SparkDispatcher

import scala.util.Try
import scala.concurrent.{Future, ExecutionContext}
import scala.concurrent.duration._

import com.typesafe.config._

import org.scalatra._
import org.scalatra.FutureSupport
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._


case class SparkJobOptions(
  minSupport: Option[Double],
  minConfidence: Option[Double]
)

class SparkDispatchServlet extends ScalatraServlet with FutureSupport with JacksonJsonSupport {

	val conf = ConfigFactory.load()

	implicit val executor =  ExecutionContext.global
	override val asyncTimeout = conf.getInt("spark-server.timeout") seconds
  protected implicit lazy val jsonFormats: Formats = DefaultFormats

	post("/") {
      contentType = formats("json")
      val sparkJobOptions = parsedBody.extract[SparkJobOptions]
	    Future(SparkDispatcher.postRequest(sparkJobOptions))
  	}

}
