package actors


import anorm.SqlParser.{get ⇒ pars}
import anorm._
import models.{ScrapeJobStatus,ScrapeJob, ScrapeJobRepo, ScrapeJobResource}
import akka.actor.Actor

/**
 * Author: @aguestuser
 * Date: 4/3/15
 */

sealed trait ScrapeJobAsk
case object Request extends ScrapeJobAsk
case class Quit(jr: ScrapeJobResource) extends ScrapeJobAsk
case class Complete(jr: ScrapeJobResource) extends ScrapeJobAsk

sealed trait ScrapeJobTell
case class NextScrapeJob(jr: ScrapeJobResource) extends ScrapeJobTell
case object WaitForScrapeJob extends ScrapeJobTell
case object NoScrapeJob extends ScrapeJobTell

class ScrapeJobSupervisor(repo: ScrapeJobRepo) extends Actor {
  val jobResource = {
    pars[Long]("id") ~
    pars[String]("ip") ~
    pars[String]("status") ~
    pars[Long]("worker_id") map {
      case id ~ ip ~ status ~ wid ⇒
        ScrapeJobResource(id, ip, ScrapeJobStatus.withName(status), wid) }
  }

  var jobs = ScrapeJob.getIncomplete(repo)

  def receive = {
    case Request ⇒
      sender ! NextScrapeJob(jobs.head)
      jobs = jobs.tail
    case Quit(jr) ⇒
      sender ! NoScrapeJob
      jobs = jr :: jobs
    case Complete(jr) ⇒
      sender ! NextScrapeJob(jobs.head)
      jobs = jobs.tail

  }
}
