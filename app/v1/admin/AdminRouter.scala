package v1.admin

import java.util.UUID

import javax.inject.Inject
import play.api.mvc.Results
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._
import utils.extractors.uuid

/**
  * Routes and URLs to the PostResource controller.
  */
class AdminRouter @Inject()(controller: AdminController) extends SimpleRouter {

  override def routes: Routes = {
    // case POST(p"/") =>
    //  controller.save()


    case GET(p"/login") =>
      controller.loginGet()




    case POST(p"/login") =>
      controller.loginPost()


  }
}
