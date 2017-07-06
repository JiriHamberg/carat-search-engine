
package caratAPIPrototype.servlets

import caratAPIPrototype.services.SparkDispatcher

import scala.util.Try
import scala.concurrent.{Future, ExecutionContext}
import scala.concurrent.duration._

import com.typesafe.config._

import org.scalatra._
import org.scalatra.FutureSupport
import scalate.ScalateSupport
import org.fusesource.scalate.{ TemplateEngine, Binding }
import org.fusesource.scalate.layout.DefaultLayoutStrategy

class SparkDispatchServlet extends ScalatraServlet with ScalateSupport with FutureSupport{

	val conf = ConfigFactory.load()

	implicit val executor =  ExecutionContext.global
	override val asyncTimeout = conf.getInt("spark-server.timeout") seconds

	post("/") {
	    contentType = "text/html"
	    Future(SparkDispatcher.postRequest())
	    //Future("HELLO FROM DISPATCHER")
    	//ssp("/base", "layout" -> "", "contextPath" -> request.getContextPath())
  	}

}