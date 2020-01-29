
package com.maxyspark.something.generated.user

import scala.concurrent.{ExecutionContext, Future}

import java.util.UUID
import javax.inject.Inject

import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

import com.arlabs.rapidplay.abstraction.{RapidBaseController, RapidControllerComponents}

import com.arlabs.rapidplay.utils.TempFile
import com.arlabs.rapidplay.utils.ExportUtil
import play.api.http.{DefaultFileMimeTypes, DefaultFileMimeTypesProvider, FileMimeTypesConfiguration}

import com.arlabs.rapidplay.definitions.{ApplicationResult}

import com.arlabs.rapidplay.utils.extractors.uuid
import play.api.libs.json.{JsError, JsSuccess, JsObject}
import play.api.mvc.{Action, AnyContent, Result, Results}

import hooks.{AppAction, AppRequest}


case class Controller @Inject()(cc: RapidControllerComponents, model: Model)(implicit val executionContext: ExecutionContext) extends RapidBaseController(cc) {
/*def getAll(): Action[AnyContent] =  RapidAction.async{implicit request =>
  model.get()
}

def get(id: UUID): Action[AnyContent] = RapidAction.async { implicit request =>
  model.get(id)
}*/

def create()(implicit request: AppRequest): Future[Result] = {
  request.body.asJson match {
    case Some(jsValue) =>
      jsValue.validate[UserCreate] match {
        case s: JsSuccess[UserCreate] =>
          model.create(s.get)
        case e: JsError =>
          Future{play.api.mvc.Results.BadRequest(s"Invalid Json in body. $e")}
      }
    case None =>
      Future{play.api.mvc.Results.BadRequest("Request not in Json format")}
  }
}

  def update(id: UUID)(implicit request: AppRequest): Future[Result] =   {
    request.body.asJson match {
      case Some(jsValue) =>
        jsValue.validate[JsObject] match {
          case s: JsSuccess[JsObject] =>
            model.update(id, s.get)
          case e: JsError =>
            Future {
              ApplicationResult.BadRequest(s"Invalid Json in body. $e")
            }
        }
      case None =>
        Future {
          ApplicationResult.BadRequest("Request not in Json format")
        }
    }
  }

  def update()(implicit request: AppRequest): Future[Result] = {
    request.body.asJson match {
      case Some(jsValue) =>
        jsValue.validate[JsObject] match {
          case s: JsSuccess[JsObject] =>
            model.update(s.get)
          case e: JsError =>
            Future {
              ApplicationResult.BadRequest(s"Invalid Json in body. $e")
            }
        }
      case None =>
        Future {
          ApplicationResult.BadRequest("Request not in Json format")
        }
    }
  }

def delete(id: UUID): Action[AnyContent] =  RapidAction.async { implicit request =>
  Future {
    Ok(s"Delete ${id.toString} working")
  }
}
}

/**
* Auto generated coded by RapidPlay
* Altering this code will yield malfunctioning of whole system
*/
class Router @Inject()(controller: Controller, appAction: AppAction, model: Model)(implicit val executionContext: ExecutionContext) extends SimpleRouter {
  override def routes: Routes = {
    case GET(p"/aggr") =>
      appAction.run {
        implicit request =>
          model.getAggr()
      }

      case GET(p"/export") =>
      appAction.run {
        implicit request =>
        model.getDirect("{where:{}}").map(user => {

        val tempFileName = "User.xls"
        val tempFile =  TempFile.create("User", ".xls")
        
        ExportUtil.exportToExcel(User.keys, user ,tempFile)
        
        implicit val mimeType =  new DefaultFileMimeTypesProvider(FileMimeTypesConfiguration(
                  Map("xls" -> "application/vnd.ms-excel")
                )).get
                
        Results.Ok.sendFile(tempFile,fileName  = _ => tempFile.getName)

        })
      }

    case GET(p"/projected") =>
      appAction.run{
        implicit request =>
          model.getProjected()
      }

    case GET(p"/count") =>
      appAction.run{
        implicit request =>
          model.count()
      }

    case GET(p"/") =>
      appAction.run{
        implicit request =>
          model.get()
      }
    case GET(p"/${uuid(id)}") =>
      appAction.run{
        implicit request =>
          model.get(id)
      }
    case POST(p"/") =>
      appAction.run{
        implicit request =>
          controller.create()
      }

    case PATCH(p"/${uuid(id)}") =>
      appAction.run{
        implicit request =>
         controller.update(id)
      }

    case PATCH(p"/") =>
      appAction.run{
        implicit request =>
         controller.update()
      }


    case DELETE(p"/${uuid(id)}") =>
      appAction.run {
        implicit request =>
          model.delete(id)
      }

    case DELETE(p"/") =>
      appAction.run {
        implicit request =>
          model.delete()
      }

    
   }
}
     