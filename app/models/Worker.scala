package models


/**
 * Author: aguestuser
 * Date: 4/3/15
 */

case class Worker(id: Long, ip: String, status: WorkerStatus.WorkerStatus)

object WorkerStatus extends Enumeration {
  type WorkerStatus = Value
  val Idle, Busy, Awaiting, Dead = Value
}


object Worker {

//  implicit val workerFormat: Format[Worker] = (
//    (JsPath \ "id").format[Long] and
//    (JsPath \ "ip").format[String]
//      (JsPath \ "status").format[WorkerStatus]
//  )(Worker.apply, unlift(Worker.unapply))

}

