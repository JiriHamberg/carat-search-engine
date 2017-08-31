
package caratAPIPrototype.servlets

import scala.util.Try

import org.scalatra._
import scalate.ScalateSupport
//import org.fusesource.scalate.{ TemplateEngine, Binding }
//import org.fusesource.scalate.layout.DefaultLayoutStrategy

class MainServlet extends ScalatraServlet with ScalateSupport{

  get("/") {
    contentType = "text/html"
    ssp("templates/views/base.ssp", "layout" -> "", "contextPath" -> request.getContextPath())
  }

}
