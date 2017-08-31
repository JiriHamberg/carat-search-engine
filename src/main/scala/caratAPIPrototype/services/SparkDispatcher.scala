package caratAPIPrototype.services

import scalaj.http.{Http, HttpOptions}
import com.typesafe.config._
import scala.concurrent.duration._


case class SparkJobOptions(
  minSupport: Option[Double],
  minConfidence: Option[Double]
)


object SparkDispatcher {
	val conf = ConfigFactory.load()
	val sparkBackendProtocol = conf.getString("spark-server.protocol")
	val sparkBackendAddr = conf.getString("spark-server.address")
	val sparkBackendPort = conf.getString("spark-server.port")
	val sparkBackendTimeout = conf.getInt("spark-server.timeout")

	def postRequest(options: SparkJobOptions): String = {
		val request = Http(s"${sparkBackendProtocol}://${sparkBackendAddr}:${sparkBackendPort}")
			.header("Content-Type", "application/json")
			.header("Charset", "UTF-8")
			.header("Accept", "application/json")
			.option(HttpOptions.readTimeout(sparkBackendTimeout.seconds.toMillis.toInt))
      .param("minSupport", options.minSupport.map(_.toString).getOrElse(""))
      .param("minConfidence", options.minConfidence.map(_.toString).getOrElse(""))
			.asString
		request.body
	}

}

