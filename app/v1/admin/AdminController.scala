/**
  * @author Ravinder Payal <mail@ravinderpayal.com>
  * @since v1 2017-11-07
  */
package v1.admin

import scala.collection.immutable.ListMap
import javax.inject.{Inject, Provider}
import play.api.data.Form
import play.api.data.Forms.{email, longNumber, mapping, number, optional, text}
import play.api.http.Status
import play.api.libs.json._
import play.api.mvc._
import com.arlabs.rapidplay.generator.{ModelConfig, StartGenerating}
import utils.{Config, Email}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

case class PanellistInput(title: String, content: String, contentHtml: String, lastPinged: Int)
case class AddEmailFormInput(label: String, emails: String)
/**
  *  Name:- AdminController
  *  Jobs:- Login, AddRetrieve
  *
*/
class AdminController @Inject()(emailer: Email,
                                config: Config,
                                routerProvider: Provider[AdminRouter],
                                cc: AdminControllerComponents,
                                start: StartGenerating)(implicit ec: ExecutionContext)
    extends AdminBaseController(cc) with play.api.i18n.I18nSupport {



  lazy val AddEmailForm: Form[AddEmailFormInput] = Form(mapping(
    "label" -> text,
    "emails" -> text
  )(AddEmailFormInput.apply)(AddEmailFormInput.unapply))





  def loginGet = Action{ implicit request =>
    Results.Ok(v1.admin.html.admin_login())
  }

  /**
    *
    * @return
    */
  def loginPost():Action[AnyContent] = Action.async{ implicit request =>
      Future {
        Ok("Body not found")
      }
  }








  private def verifyLogin[A](a:Future[Result])(implicit request:Request[A]):Future[Result] = {
    //block(request)
    request.session.get("adminEmail") match {
      case Some(email) if config.admins.contains(email)=>
          a
      case _ => Future.successful(Forbidden(v1.admin.html.admin_login()))
    }
  }

}
