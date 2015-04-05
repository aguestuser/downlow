package models

import models.ScrapeJobStatus.ScrapeJobStatus

import scalaz.Reader

/**
 * Author: @aguestuser
 * Date: 4/3/15
 */

case class ScrapeJob(bbl: String, status: ScrapeJobStatus, worker: Worker)

object ScrapeJobStatus extends Enumeration {
  type ScrapeJobStatus = Value
  val Awaiting = Value("awaiting")
  val InProgress = Value("in progress")
  val Terminated = Value("terminated")
  val Complete = Value("complete")
}

object ScrapeJob {

  def get(id: Long) = Reader{ (repo: ScrapeJobRepo) ⇒ repo.get(id) }
  def getAll = Reader { (repo: ScrapeJobRepo) ⇒ repo.getAll }
  def getIncomplete = Reader { (repo: ScrapeJobRepo) ⇒ repo.getIncomplete }
  def create(p: ScrapeJob) = Reader { (repo: ScrapeJobRepo) ⇒ repo.create(p) }
  def edit(id: Long, edits: ScrapeJob) = Reader { (repo: ScrapeJobRepo) ⇒ repo.edit(id,edits) }
  def delete(id: Long) = Reader { (repo: ScrapeJobRepo) ⇒ repo.delete(id) }
}
