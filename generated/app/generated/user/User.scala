
package com.maxyspark.something.generated.user

import play.api.libs.json._
import play.api.mvc.Results
import scala.concurrent.ExecutionContext.Implicits.global
import com.arlabs.rapidplay.definitions.{ApplicationResult, DataObject, ServiceUser}
import scala.concurrent.Future
import java.util.UUID
import java.util.Date

import com.arlabs.rapidplay.enums.UserRole

// --- Custom imports start ---

// --- Custom imports end ---

case class User(location:String ,
                override val name : com.arlabs.rapidplay.utils.PersonName,
                override val email : Option[com.arlabs.rapidplay.utils.Email],
                override val mobile : Option[String],
                override val __password__ : String,
                override val id : UUID,
                override val time : Date,
                override val updateTime : Option[Date],
                override val __manager__ : Set[UUID],
                override val __readableTo__ : Set[String],
                override val __updatableBy__ : Set[String],
                override val __deletableBy__ : Set[String],
                override val __aclEditableBy__ : Set[String],
							  override val __roles__ : Set[String]) extends DataObject with ServiceUser{
  def toPublic = UserPublic(name, email, mobile, __roles__, id, time, updateTime, location)
  def toPublicOk = ApplicationResult.Ok(toPublic.toJsValue)
}

object User {
  implicit val format: OFormat[User] = Json.format[User]
  val keys: Set[String]= Set(Set("name.first", "name.middle", "name.last", "email", "password"), Set("location")).flatten

}

case class UserCreate(location:String ,
                name : com.arlabs.rapidplay.utils.PersonName,
                email : Option[com.arlabs.rapidplay.utils.Email],
                mobile : Option[String],
                password : String,
                roles: Set[String]
						){
  def toUser(implicit session: com.arlabs.rapidplay.abstraction.UserSession[com.maxyspark.something.generated.user.User]): User = {
            val id = UUID.randomUUID()
           User(location, name, email, mobile, password, id, new java.util.Date(), None, Set(session.id),
            Set(UserRole.$Inherit.toString, s"User$id"), Set(UserRole.$Inherit.toString, s"User$id"),
            Set(UserRole.$Inherit.toString), Set(UserRole.$Inherit.toString), roles) 
          }

  
}

object UserCreate {
  
  implicit val format: OFormat[UserCreate] = Json.format[UserCreate]
}


case class UserPublic(name: com.arlabs.rapidplay.utils.PersonName, email: Option[com.arlabs.rapidplay.utils.Email], mobile: Option[String], roles: Set[String], id : UUID, time: Date, updateTime: Option[Date]
           ,location:String) {
  def toJsValue: JsValue = Json.toJson(this)
  override def toString: String = toJsValue.toString()
}
object UserPublic {
  implicit val format: OFormat[UserPublic] = Json.format[UserPublic]
}

