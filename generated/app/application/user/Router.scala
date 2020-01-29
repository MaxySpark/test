
package com.maxyspark.something.application.user

import scala.concurrent.{ExecutionContext, Future}

import java.util.UUID
import javax.inject.Inject

import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

import com.arlabs.rapidplay.abstraction.{RapidBaseController, RapidControllerComponents}

import com.arlabs.rapidplay.definitions.{ApplicationResult}

import com.arlabs.rapidplay.utils.extractors.uuid
import play.api.libs.json.{JsError, JsSuccess, JsObject}
import play.api.mvc.{Action, AnyContent, Result, Results}
import hooks.{AppAction, AppRequest}

import com.maxyspark.something.generated.user._


class Router @Inject()(controller: Controller, appAction: AppAction, model: Model)(override implicit val executionContext: ExecutionContext) extends com.maxyspark.something.generated.user.Router(controller, appAction, model) {
   override def routes: Routes = super.routes orElse  {
     case POST(p"/import") =>
         appAction.run {
           implicit request =>
             Future {
               Results.Ok(s"Import is  working")
             }
         }
     }
   }
