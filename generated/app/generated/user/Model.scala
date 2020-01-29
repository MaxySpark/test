package com.maxyspark.something.generated.user

import javax.inject.Inject
import java.util.UUID

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.collection.immutable

import play.api.mvc.{Results}

import com.arlabs.rapidplay.definitions.ModelDefinition
import com.arlabs.rapidplay.abstraction.UserSession
import com.arlabs.rapidplay.definitions.ApplicationResult

import com.arlabs.rapidplay.enums.UserRole
import play.api.libs.json.{Json, JsObject,JsString, JsNumber}
import reactivemongo.play.json._
import reactivemongo.play.json.collection._


import JSONBatchCommands.AggregationFramework.{AggregationResult, Ascending, Group, Last, Match, Sort, SortOrder, SumField}
import reactivemongo.core.commands.PipelineOperator


class Model @Inject()(repositoryImpl: RepositoryImpl)
 extends ModelDefinition[User, com.maxyspark.something.generated.user.User, hooks.AppRequest] {
  /* validators start */
  
  // implicit val modelType = "USER"
  private def _p_isUniqueCheck_email(user: User) (implicit appRequest: hooks.AppRequest) =  
            if(user.email.isDefined) repository.getOne(
              Json.obj("email"-> user.email)
            ).map(_.isEmpty) else Future{true}
  private def _p_isUniqueCheck_mobile(user: User) (implicit appRequest: hooks.AppRequest) =  
            if(user.mobile.isDefined) repository.getOne(
              Json.obj("mobile"-> user.mobile)
            ).map(_.isEmpty) else Future{true}

  
  /* validators end */
  /* Dynamic Acl Handling */
  

  def dynamicAcls(implicit userSession: UserSession[com.maxyspark.something.generated.user.User]): immutable.HashMap[String, JsObject] = immutable.HashMap(
    
  )

  val readDynamicAcl = List()
  val createDynamicAcl = List()
  val updateDynamicAcl = List()
  val deleteDynamicAcl = List()
  val aclDynamicAcl = List()

  /* Dynamic Acl Handling ends */

	
  override def createHooks(implicit r: hooks.AppRequest): List[User => Unit] = List()

  override protected val readAcl: List[String] = List(UserRole.$SuperAdmin.toString)
  override protected val createAcl = List(UserRole.$SuperAdmin.toString)
  override protected val updateAcl = List(UserRole.$SuperAdmin.toString)
  override protected val deleteAcl = List(UserRole.$SuperAdmin.toString)

  def createValidators(implicit userSession: UserSession[com.maxyspark.something.generated.user.User], appRequest: hooks.AppRequest): List[User => Future[Boolean]] = List(_p_isUniqueCheck_email, _p_isUniqueCheck_mobile)
  def readValidators(implicit userSession: UserSession[com.maxyspark.something.generated.user.User]): List[User => Future[Boolean]] = List()

  override def repository: RepositoryImpl = repositoryImpl
  override def validate(model: User)(implicit session: com.arlabs.rapidplay.abstraction.UserSession[com.maxyspark.something.generated.user.User]): Future[Boolean] = Future{
    true
  }

  val fieldProperties: Set[com.arlabs.rapidplay.definitions.PropertyDefinition[AnyRef]] = Set()
  val fields: Set[String] = Set("location")
  val fieldsPublic: Set[String] = Set("location")
  val fieldsPrivate: Set[String] = Set()

  def create(userCreate: UserCreate)(implicit request: hooks.AppRequest): Future[play.api.mvc.Result] = {
    request.user match {
      case Some(session) =>
        implicit val implicitSession: UserSession[com.maxyspark.something.generated.user.User] = session
        val user = userCreate.toUser
        executeCreateHooks(user)
        createValidate(user).flatMap(if(_) repository.create(user).map(if (_) user.toPublicOk else play.api.mvc.Results.ServiceUnavailable) else Future{play.api.mvc.Results.Forbidden("It seems you are not allowed to perform this action.")})
      case None =>
        Future{
          ApplicationResult.Forbidden()
        }
    }
  }

  def createDirect(userCreate: UserCreate)(implicit request: hooks.AppRequest): Future[Option[UserPublic]] = {
    request.user match {
      case Some(session) =>
        implicit val implicitSession: UserSession[com.maxyspark.something.generated.user.User] = session
        val user = userCreate.toUser
        executeCreateHooks(user)
        createValidate(user).flatMap(if(_) repository.create(user).map(if (_) Some(user.toPublic) else None) else Future{None})
      case None =>
        Future{
          None
        }
    }
  }

  def canRead(id: java.util.UUID)(implicit request: hooks.AppRequest): Future[Boolean] = {
    request.user match {
      case Some(session) =>
        implicit val implicitSession: UserSession[com.maxyspark.something.generated.user.User] = session
        val query = {
          if (session.roles.exists(readAcl.contains))
            Json.obj("id" -> id, "__readableTo__" -> UserRole.$Inherit)
          else if (session.roles.exists(readDynamicAcl.contains)) {
            val dynamicRoles = session.roles.filter(readDynamicAcl.contains).headOption
            val rdaQuery = if (dynamicRoles.isDefined) dynamicAcls.get(dynamicRoles.get).get else Json.obj()
            Json.obj("id" -> id, "__readableTo__" -> UserRole.$Inherit) ++ rdaQuery
          }
          else
              Json.obj("id" -> id, "__readableTo__" -> Json.obj("$in" -> session.roles.map{
                               role =>
                                 UserRole.getRoleValue(session).getOrElse(role, role)
                             }))
        }
        repository.getOne(query).map(_.isDefined)

        // readValidate(state).flatMap(if(_) repository.getOne(state).map(if (_)play.api.mvc.Results.Ok else play.api.mvc.Results.ServiceUnavailable) else Future{play.api.mvc.Results.Forbidden("It seems you are not allowed to perform this action.")})
      case None =>
        Future{
          false
        }
    }
  }


  


  def get(id: java.util.UUID)(implicit request: hooks.AppRequest): Future[play.api.mvc.Result] = {
    request.user match {
      case Some(session) =>
        implicit val implicitSession: UserSession[com.maxyspark.something.generated.user.User] = session
        val query = {
          if (session.roles.exists(readAcl.contains))
            Json.obj("id" -> id, "__readableTo__" -> UserRole.$Inherit)
          else if (session.roles.exists(readDynamicAcl.contains)) {
            val dynamicRoles = session.roles.filter(readDynamicAcl.contains).headOption
            val rdaQuery = if (dynamicRoles.isDefined) dynamicAcls.get(dynamicRoles.get).get else Json.obj()
            Json.obj("id" -> id, "__readableTo__" -> UserRole.$Inherit) ++ rdaQuery
          }
          else Json.obj("id" -> id, "__readableTo__" -> Json.obj("$in" -> session.roles.map{
                               role =>
                                 UserRole.getRoleValue.getOrElse(role, role)
                             }))
        }
        repository.getOne(query).map(a => if (a.isDefined) a.get.toPublicOk else ApplicationResult.NotFound(s"(User:$id)"))
      case None =>
        Future{
          ApplicationResult.Forbidden()
        }
    }
  }

  def get()(implicit request: hooks.AppRequest): Future[play.api.mvc.Result] = {
    implicit val operationType = "READ"
    val filter = genQuery(request.request.queryString.getOrElse("filter", Vector("{}")).head)
    val order = request.request.queryString.getOrElse[Seq[String]]("order", Seq("1")).head.toInt
    val skipN = request.request.queryString.getOrElse[Seq[String]]("skipN", Seq("0")).head.toInt
    val limitN = request.request.queryString.getOrElse[Seq[String]]("limitN", Seq("10")).head.toInt
    request.user match {
      case Some(session) =>
        implicit val implicitSession: UserSession[com.maxyspark.something.generated.user.User] = session
        val query = {
          if (session.roles.exists(readAcl.contains))
              Json.obj("__readableTo__" -> UserRole.$Inherit) ++ filter
          else if (session.roles.exists(readDynamicAcl.contains)) {
            val dynamicRoles = session.roles.filter(readDynamicAcl.contains).headOption
            val rdaQuery = if (dynamicRoles.isDefined) dynamicAcls.get(dynamicRoles.get).get else Json.obj()
            Json.obj("__readableTo__" -> UserRole.$Inherit) ++ rdaQuery ++ filter
          }
          else
              Json.obj("__readableTo__" -> Json.obj("$in" -> session.roles.map{
                role =>
                  UserRole.getRoleValue.getOrElse(role, role)
              })) ++ filter
        }

        repository.get(query, order=order,  skipN, limitN).map(a => ApplicationResult.Ok(Json.toJson(a.map(_.toPublic))))
      case None =>
        Future{
          ApplicationResult.Forbidden()
        }
    }
  }

  def getProjected()(implicit request: hooks.AppRequest): Future[play.api.mvc.Result] = {
    implicit val operationType = "READ"
    val filter = genQuery(request.request.queryString.getOrElse("filter", Vector("{}")).head)
    val projection = genQueryDirect(request.request.queryString.getOrElse("projection", Vector("{id: 1}")).head)
    val order = request.request.queryString.getOrElse[Seq[String]]("order", Seq("1")).head.toInt
    val skipN = request.request.queryString.getOrElse[Seq[String]]("skipN", Seq("0")).head.toInt
    val limitN = request.request.queryString.getOrElse[Seq[String]]("limitN", Seq("10")).head.toInt
    request.user match {
      case Some(session) =>
        implicit val implicitSession: UserSession[com.maxyspark.something.generated.user.User] = session
        val query = {
          if (session.roles.exists(readAcl.contains))
              Json.obj("__readableTo__" -> UserRole.$Inherit) ++ filter
          else if (session.roles.exists(readDynamicAcl.contains)) {
            val dynamicRoles = session.roles.filter(readDynamicAcl.contains).headOption
            val rdaQuery = if (dynamicRoles.isDefined) dynamicAcls.get(dynamicRoles.get).get else Json.obj()
            Json.obj("__readableTo__" -> UserRole.$Inherit) ++ rdaQuery ++ filter
          }
          else
              Json.obj("__readableTo__" -> Json.obj("$in" -> session.roles.map{
                role =>
                  UserRole.getRoleValue.getOrElse(role, role)
              })) ++ filter
        }
        repository.get(query, projection, order=order,  skipN, limitN).map(a => ApplicationResult.Ok(a))
      case None =>
        Future{
          ApplicationResult.Forbidden()
        }
    }
  }

  def countDirect(filterQ: String)(implicit request: hooks.AppRequest): Future[Int] = {
    implicit val operationType = "READ"
    val filter = genQuery(filterQ)
    request.user match {
      case Some(session) =>
        implicit val implicitSession: UserSession[com.maxyspark.something.generated.user.User] = session
        val query = {
          if (session.roles.exists(readAcl.contains))
            Json.obj("__readableTo__" -> UserRole.$Inherit) ++ filter
          else if (session.roles.exists(readDynamicAcl.contains)) {
            val dynamicRoles = session.roles.filter(readDynamicAcl.contains).headOption
            val rdaQuery = if (dynamicRoles.isDefined) dynamicAcls.get(dynamicRoles.get).get else Json.obj()
            Json.obj("__readableTo__" -> UserRole.$Inherit) ++ rdaQuery ++ filter
          }
          else
            Json.obj("__readableTo__" -> Json.obj("$in" -> session.roles.map{
              role =>
                UserRole.getRoleValue.getOrElse(role, role)
            })) ++ filter
        }

        repository.count(query)
      case None =>
        Future{0}
    }
  }


  def count()(implicit request: hooks.AppRequest): Future[play.api.mvc.Result] = {
    implicit val operationType = "READ"
    val filter = genQuery(request.request.queryString.getOrElse("filter", Vector("{}")).head)
    request.user match {
      case Some(session) =>
        implicit val implicitSession: UserSession[com.maxyspark.something.generated.user.User] = session
        val query = {
          if (session.roles.exists(readAcl.contains))
              Json.obj("__readableTo__" -> UserRole.$Inherit) ++ filter
          else if (session.roles.exists(readDynamicAcl.contains)) {
            val dynamicRoles = session.roles.filter(readDynamicAcl.contains).headOption
            val rdaQuery = if (dynamicRoles.isDefined) dynamicAcls.get(dynamicRoles.get).get else Json.obj()
            Json.obj("__readableTo__" -> UserRole.$Inherit) ++ rdaQuery ++ filter
          }
          else
              Json.obj("__readableTo__" -> Json.obj("$in" -> session.roles.map{
                role =>
                  UserRole.getRoleValue.getOrElse(role, role)
              })) ++ filter
        }

        repository.count(query).map(a => ApplicationResult.Ok(JsNumber(a)))
      case None =>
        Future{
          ApplicationResult.Forbidden()
        }
    }
  }

  def getAggr()(implicit request: hooks.AppRequest): Future[play.api.mvc.Result] = {
    implicit val operationType = "READ"
    val filter = genQuery(request.request.queryString.getOrElse("filter", Vector("{}")).head)
    val query = genAggrQuery(request.request.queryString.getOrElse("aggregate", Vector("{}")).head)
    request.user match {
      case Some(session) =>
        implicit val implicitSession: UserSession[com.maxyspark.something.generated.user.User] = session
        val queryFinal: JsObject = if (session.roles.exists(readAcl.contains)) {
          Json.obj("__readableTo__" -> UserRole.$Inherit) ++ filter
        } else if (session.roles.exists(readDynamicAcl.contains)) {
          val dynamicRoles = session.roles.filter(readDynamicAcl.contains).headOption
          val rdaQuery = if (dynamicRoles.isDefined) dynamicAcls.get(dynamicRoles.get).get else Json.obj()
          Json.obj("__readableTo__" -> UserRole.$Inherit) ++ rdaQuery ++ filter
        } else {
          Json.obj("__readableTo__" -> Json.obj("$in" -> session.roles.map {
            role =>
              UserRole.getRoleValue.getOrElse(role, role)
          })) ++ filter
        }
        repositoryImpl.getAggr(Match(queryFinal),
          query).map(res => ApplicationResult.Ok(Json.toJson(res)))
      case None =>
        Future{
          ApplicationResult.Forbidden()
        }
    }
  }

  def getDirect(filterQ: String, order: Int = 1, skipN: Int = 0, limitN: Int = 10)(implicit request: hooks.AppRequest): Future[List[UserPublic]] = {
    implicit val operationType = "READ"
    val filter = genQuery(filterQ)
    val query = request.user match {
      case Some(session) =>
        implicit val implicitSession: UserSession[com.maxyspark.something.generated.user.User] = session
        if (session.roles.exists(readAcl.contains))
          Json.obj("__readableTo__" -> UserRole.$Inherit) ++ filter
        else if (session.roles.exists(readDynamicAcl.contains)) {
          val dynamicRoles = session.roles.filter(readDynamicAcl.contains).headOption
          val rdaQuery = if (dynamicRoles.isDefined) dynamicAcls.get(dynamicRoles.get).get else Json.obj()
          Json.obj("__readableTo__" -> UserRole.$Inherit) ++ rdaQuery ++ filter
        }
        else
          Json.obj("__readableTo__" -> Json.obj("$in" -> session.roles.map{
            role =>
              UserRole.getRoleValue.getOrElse(role, role)
          })) ++ filter
      case None =>
        if (readAcl.contains(UserRole.$Unauthenticated.toString)) {
          println("inhereting..")
          Json.obj("__readableTo__" -> UserRole.$Inherit) ++ filter
        }
        else
          Json.obj("__readableTo__" -> Json.obj("$in" ->  List(UserRole.$Unauthenticated.toString))) ++ filter
    }
    println(query.toString())
    repository.get(query, order=order,  skipN, limitN).map(_.map(_.toPublic))
  }


  def update(id: UUID, updateQ: JsObject)(implicit request: hooks.AppRequest): Future[play.api.mvc.Result] = {
    checkQuery(updateQ)
    require(updateQ.keys.forall(User.keys.contains), " Tried keys are: " + updateQ.keys.mkString(", ") +  "Difference is: " + updateQ.keys.diff(User.keys).mkString(", "))
    request.user match {
      case Some(session) =>
        implicit val implicitSession: UserSession[com.maxyspark.something.generated.user.User] = session
        val query: JsObject = {
          if (session.roles.exists(updateAcl.contains))
            Json.obj("__readableTo__" -> UserRole.$Inherit)
          else if (session.roles.exists(updateDynamicAcl.contains)) {
            val dynamicRoles = session.roles.find(updateDynamicAcl.contains)
            val rdaQuery = if (dynamicRoles.isDefined) dynamicAcls.get(dynamicRoles.get).get else Json.obj()
            Json.obj("__readableTo__" -> UserRole.$Inherit) ++ rdaQuery
          }
          else
            Json.obj("__readableTo__" -> Json.obj("$in" -> session.roles.map{
              role =>
                UserRole.getRoleValue.getOrElse(role, role)
            }))
        }

        repository.update(query ++ Json.obj("id" -> id), Json.obj("$set" -> updateQ)).map(a => if (a) ApplicationResult.Ok() else ApplicationResult.ServiceUnavailable())
      case None =>
        Future{
          ApplicationResult.Forbidden()
        }
    }
  }

  def updateDirect(filterQ: String = "{}", updateQ: JsObject)(implicit request: hooks.AppRequest): Future[Boolean] = {
    checkQuery(updateQ)
    require(updateQ.keys.forall(User.keys.contains), " Tried keys are: " + updateQ.keys.mkString(", ") +  "Difference is: " + updateQ.keys.diff(User.keys).mkString(", "))
    val filter = genQuery(filterQ)
    request.user match {
      case Some(session) =>
        implicit val implicitSession: UserSession[com.maxyspark.something.generated.user.User] = session
        val query: JsObject = {
          if (session.roles.exists(updateAcl.contains))
            Json.obj("__readableTo__" -> UserRole.$Inherit) ++ filter
          else if (session.roles.exists(updateDynamicAcl.contains)) {
            val dynamicRoles = session.roles.filter(updateDynamicAcl.contains).headOption
            val udaQuery = if (dynamicRoles.isDefined) dynamicAcls.get(dynamicRoles.get).get else Json.obj()
            Json.obj( "__readableTo__" -> UserRole.$Inherit) ++ udaQuery ++ filter
          }
          else
            Json.obj("__readableTo__" -> Json.obj("$in" -> session.roles.map{
              role =>
                UserRole.getRoleValue.getOrElse(role, role)
            })) ++ filter
        }

        repository.update(query, Json.obj("$set" -> updateQ))
      case None =>
        Future{
          false
        }
    }
  }

  def update(updateQ: JsObject)(implicit request: hooks.AppRequest): Future[play.api.mvc.Result] = {
    checkQuery(updateQ)
    require(updateQ.keys.forall(User.keys.contains), " Tried keys are: " + updateQ.keys.mkString(", ") +  "Difference is: " + updateQ.keys.diff(User.keys).mkString(", "))
    val filter = genQuery(request.request.queryString.getOrElse("filter", Vector("{}")).head)
    request.user match {
      case Some(session) =>
        implicit val implicitSession: UserSession[com.maxyspark.something.generated.user.User] = session
        val query: JsObject = {
          if (session.roles.exists(updateAcl.contains))
            Json.obj("__readableTo__" -> UserRole.$Inherit) ++ filter
          else if (session.roles.exists(updateDynamicAcl.contains)) {
            val dynamicRoles = session.roles.filter(updateDynamicAcl.contains).headOption
            val udaQuery = if (dynamicRoles.isDefined) dynamicAcls.get(dynamicRoles.get).get else Json.obj()
            Json.obj( "__readableTo__" -> UserRole.$Inherit) ++ udaQuery ++ filter
          }
          else
            Json.obj("__readableTo__" -> Json.obj("$in" -> session.roles.map{
              role =>
                UserRole.getRoleValue.getOrElse(role, role)
            })) ++ filter
        }

        repository.update(query, Json.obj("$set" -> updateQ)).map(a => if (a) ApplicationResult.Ok() else ApplicationResult.ServiceUnavailable())
      case None =>
        Future{
          ApplicationResult.Forbidden()
        }
    }
  }

  def delete(id: UUID)(implicit request: hooks.AppRequest): Future[play.api.mvc.Result] = {
    request.user match {
      case Some(session) =>
        implicit val implicitSession: UserSession[com.maxyspark.something.generated.user.User] = session
        val query: JsObject = {
          if (session.roles.exists(deleteAcl.contains))
            Json.obj("__readableTo__" -> UserRole.$Inherit)
          else if (session.roles.exists(deleteDynamicAcl.contains)) {
            val dynamicRoles = session.roles.filter(deleteDynamicAcl.contains).headOption
            val ddaQuery = if (dynamicRoles.isDefined) dynamicAcls.get(dynamicRoles.get).get else Json.obj()
            Json.obj("__readableTo__" -> UserRole.$Inherit) ++ ddaQuery
          }
          else
            Json.obj("__readableTo__" -> Json.obj("$in" -> session.roles.map{
              role =>
                UserRole.getRoleValue.getOrElse(role, role)
            }))
        }
        repository.delete(query ++ Json.obj("id" -> id)).map(a => if (a) ApplicationResult.Ok() else ApplicationResult.ServiceUnavailable())
      case None =>
        Future{
          ApplicationResult.Forbidden()
        }
    }
  }

  def delete()(implicit request: hooks.AppRequest): Future[play.api.mvc.Result] = {
    val filter = genQuery(request.request.queryString.getOrElse("filter", Vector("{}")).head)
    request.user match {
      case Some(session) =>
        implicit val implicitSession: UserSession[com.maxyspark.something.generated.user.User] = session
        val query: JsObject = {
          if (session.roles.exists(deleteAcl.contains))
            Json.obj("__readableTo__" -> UserRole.$Inherit) ++ filter
          else if (session.roles.exists(deleteDynamicAcl.contains)) {
            val dynamicRoles = session.roles.filter(deleteDynamicAcl.contains).headOption
            val udaQuery = if (dynamicRoles.isDefined) dynamicAcls.get(dynamicRoles.get).get else Json.obj()
            Json.obj( "__readableTo__" -> UserRole.$Inherit) ++ udaQuery ++ filter
          }
          else
            Json.obj("__readableTo__" -> Json.obj("$in" -> session.roles.map{
              role =>
                UserRole.getRoleValue.getOrElse(role, role)
            })) ++ filter
        }

        repository.delete(query).map(a => if (a) ApplicationResult.Ok() else ApplicationResult.ServiceUnavailable())
      case None =>
        Future{
          ApplicationResult.Forbidden()
        }
    }
  }

  def deleteDirect(filterQ: String = "{id: 0}")(implicit request: hooks.AppRequest): Future[Boolean] = {
    val filter = genQuery(filterQ)
    request.user match {
      case Some(session) =>
        implicit val implicitSession: UserSession[com.maxyspark.something.generated.user.User] = session
        val query: JsObject = {
          if (session.roles.exists(deleteAcl.contains))
            Json.obj("__readableTo__" -> UserRole.$Inherit) ++ filter
          else if (session.roles.exists(deleteDynamicAcl.contains)) {
            val dynamicRoles = session.roles.filter(deleteDynamicAcl.contains).headOption
            val udaQuery = if (dynamicRoles.isDefined) dynamicAcls.get(dynamicRoles.get).get else Json.obj()
            Json.obj( "__readableTo__" -> UserRole.$Inherit) ++ udaQuery ++ filter
          }
          else
            Json.obj("__readableTo__" -> Json.obj("$in" -> session.roles.map{
              role =>
                UserRole.getRoleValue.getOrElse(role, role)
            })) ++ filter
        }

        repository.delete(query)
      case None =>
        Future{
          false
        }
    }
  }



}