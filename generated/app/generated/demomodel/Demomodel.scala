
package com.maxyspark.something.generated.demomodel

import play.api.libs.json._
import play.api.mvc.Results
import scala.concurrent.ExecutionContext.Implicits.global
import com.arlabs.rapidplay.definitions.{ApplicationResult, DataObject}
import scala.concurrent.Future
import java.util.UUID
import java.util.Date

import com.arlabs.rapidplay.enums.UserRole

// --- Custom imports start ---

// --- Custom imports end ---


case class Demomodel(location:String,
                     override val id : UUID,
                     override val time : Date,
                     override val updateTime : Option[Date],
                     override val __manager__ : Set[UUID],
                     override val __readableTo__ : Set[String],
                     override val __updatableBy__ : Set[String],
                     override val __deletableBy__ : Set[String],
                     override val __aclEditableBy__ : Set[String]) extends DataObject {
  def toPublic = DemomodelPublic(id, time, updateTime, location)
  def toPublicOk = ApplicationResult.Ok(toPublic.toJsValue)
}

object Demomodel {
  implicit val format: OFormat[Demomodel] = Json.format[Demomodel]

  val keys = Set(Set("location")).flatten.toSet
  }

case class DemomodelCreate(location:String){
  def toDemomodel(implicit session: com.arlabs.rapidplay.abstraction.UserSession[com.maxyspark.something.generated.user.User]): Demomodel =
           Demomodel(location, UUID.randomUUID(), new java.util.Date(), None, Set(session.id),
            Set(UserRole.$Inherit.toString), Set(UserRole.$Inherit.toString),
            Set(UserRole.$Inherit.toString), Set(UserRole.$Inherit.toString))
  /*
  def toStateWA(rt: Set[com.arlabs.rapidplay.enums.UserRole.UserRole], ub: Set[com.arlabs.rapidplay.enums.UserRole.UserRole]) : Demomodel = {
    val uuid = UUID.randomUUID()
    Demomodel(location, uuid, new Date(), None, Set(),
      Set(UserRole.$Inherit.toString),
      Set(UserRole.$Inherit.toString),
      Set(UserRole.$Inherit.toString),
      Set(UserRole.$Inherit.toString)
    )
  }*/
}
object DemomodelCreate {
  implicit val format: OFormat[DemomodelCreate] = Json.format[DemomodelCreate]
}


case class DemomodelPublic(id : UUID, time: Date, updateTime: Option[Date]
           ,location:String) {
  def toJsValue: JsValue = Json.toJson(this)
  override def toString: String = toJsValue.toString()
}
object DemomodelPublic {
  implicit val format: OFormat[DemomodelPublic] = Json.format[DemomodelPublic]
}

