package caratAPIPrototype.services

import scalaj.http.{Http, HttpOptions}
import com.typesafe.config._
import scala.concurrent.duration._


object SparkDispatcher {
	val conf = ConfigFactory.load()
	val sparkBackendProtocol = conf.getString("spark-server.protocol")
	val sparkBackendAddr = conf.getString("spark-server.address")
	val sparkBackendPort = conf.getString("spark-server.port")
	val sparkBackendTimeout = conf.getInt("spark-server.timeout")

	def postRequest(): String = {
		val request = Http(s"${sparkBackendProtocol}://${sparkBackendAddr}:${sparkBackendPort}")
			.header("Content-Type", "application/json")
			.header("Charset", "UTF-8")
			.option(HttpOptions.readTimeout(sparkBackendTimeout.seconds.toMillis.toInt))
			.asString
		request.body
	}

}

