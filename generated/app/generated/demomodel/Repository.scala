package com.maxyspark.something.generated.demomodel
import java.util.{Date, UUID}
import javax.inject.{Inject, Singleton}
import akka.actor.ActorSystem
import play.api.libs.concurrent.CustomExecutionContext
import play.api.libs.json._
import play.api.{Logger, MarkerContext}
import play.modules.reactivemongo.ReactiveMongoApi
import play.mvc.Http.Request
import reactivemongo.api.{Cursor, QueryOpts}
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.api.commands.WriteResult
import reactivemongo.play.json._
import reactivemongo.play.json.collection._

import scala.concurrent.Future
import scala.language.implicitConversions
import scala.util.{Failure, Success}

class JobExecutionContext @Inject()(actorSystem: ActorSystem) extends CustomExecutionContext(actorSystem, "userRepository.dispatcher")

/**
  * A trivial implementation for the Demomodel Repository.
  *
  * A custom execution context is used here to establish that blocking operations should be
  * executed in a different thread than Play's ExecutionContext, which is used for CPU bound tasks
  * such as rendering.
  */
@Singleton
class RepositoryImpl @Inject()()(implicit ec: JobExecutionContext, val reactiveMongoApi: ReactiveMongoApi)
extends com.arlabs.rapidplay.definitions.Repository[Demomodel] {

  override def collection: Future[JSONCollection] = reactiveMongoApi.database.map(_.collection[JSONCollection](s"demomodel"))
    

  private val logger = Logger(this.getClass)

  override def getOne(filter: JsObject)(implicit mc: MarkerContext): Future[Option[Demomodel]] = {
    collection.flatMap (_.find(filter).one[Demomodel])
  }

  override def get(filter: JsObject, order: Int, skipN: Int, limitN: Int)(implicit mc: MarkerContext): Future[List[Demomodel]] = {
    val qOps = QueryOpts(skipN = skipN, batchSizeN = limitN, flagsN = 0)
    collection.flatMap (_.find(filter)
      .options(qOps)
      .sort(Json.obj("time" -> order))
      .batchSize(limitN)
      .cursor[Demomodel]()
      .collect[List](limitN,Cursor.FailOnError[List[Demomodel]]()))
  }
  override def get(filter: JsObject, order: Int, skipN: Int)(implicit mc: MarkerContext): Future[List[Demomodel]] = {
    val qOps = QueryOpts(skipN = skipN, batchSizeN = 10, flagsN = 0)
    collection.flatMap (_.find(filter)
      .options(qOps)
      .sort(Json.obj("time" -> -1))
      .batchSize(10)
      .cursor[Demomodel]()
      .collect[List](10,Cursor.FailOnError[List[Demomodel]]()))
  }


  override def create(data: Demomodel)(implicit mc: MarkerContext): Future[Boolean] = {
    collection.flatMap(_.insert(data)).map(_.ok)
  }

  override def upsert(filter: JsObject, data: Demomodel)(implicit mc: MarkerContext): Future[Boolean] = {
    collection.flatMap(_.update(filter, data, upsert =  true)).map(_.ok)
  }
  override def update(filter: JsObject, data: JsObject)(implicit mc: MarkerContext): Future[Boolean] = {
    collection.flatMap(_.update(filter, data, multi =  true)).map(_.ok)
  }
}