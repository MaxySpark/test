
        package hooks
        
        import java.io.File
        import java.util.UUID
        
        import com.arlabs.rapidplay.abstraction._
        import com.arlabs.rapidplay.enums.LoginProvider.LoginProvider
        import com.arlabs.rapidplay.definitions.ServiceUser
        import javax.inject.{Inject, Singleton}
        // import javax.xml.ws.Service
        import play.api.i18n.MessagesApi
        import play.api.libs.json.{JsError, JsSuccess, Json}
        import play.api.mvc._
        
        import scala.concurrent.{ExecutionContext, Future}
        import com.maxyspark.something.generated.user._
        import play.libs.Files
        
        
        case class AppRequest(user: Option[UserSession[User]], request: Request[AnyContent], messagesApi: MessagesApi) extends WrappedRequest(request) with RapidRequestHeader
        case class AppRequestGeneric[T](user: Option[UserSession[User]], request: Request[T], messagesApi: MessagesApi) extends WrappedRequest(request) with RapidRequestHeader {
          def asAppRequest = AppRequest(user, request.asInstanceOf[Request[AnyContent]], messagesApi)
        }
        
        @Singleton
        case class AppAction @Inject()(cc: RapidControllerComponents, repository: RepositoryImpl)(implicit val executionContext: ExecutionContext) extends RapidBaseController(cc) {
            def run(b: AppRequest => Future[Result]): Action[AnyContent] =  RapidAction.async{
              implicit request =>
                request.user match {
                  case Some(session) =>
                    repository.getOne(Json.obj("id" -> session.id)).flatMap{
                      case Some(usr) =>
                        b(AppRequest(Some(UserSession(session.id, session.loginProvider, usr.__roles__ ++ session.roles, Some(usr))), request.request, request.messagesApi))
                      case None =>
                        Future{Unauthorized}
                    }
                  case None =>
                    b(AppRequest(None, request.request, request.messagesApi))
                }
            }
        
          def run(bodyParser: BodyParser[MultipartFormData[File]])(b: AppRequestGeneric[MultipartFormData[File]] => Future[Result]) =  RapidAction(bodyParser).async(bodyParser){
            implicit request =>
              request.user match {
                case Some(session) =>
                  repository.getOne(Json.obj("id" -> session.id)).flatMap{
                    case Some(usr) =>
                      b(AppRequestGeneric(Some(UserSession(session.id, session.loginProvider, usr.__roles__ ++ session.roles, Some(usr))), request.request, request.messagesApi))
                    case None =>
                      Future{Unauthorized}
                  }
                case None =>
                  b(AppRequestGeneric(None, request.request, request.messagesApi))
              }
          }
        
        }
                        