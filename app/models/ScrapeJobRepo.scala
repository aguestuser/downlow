package models

import models.ScrapeJobStatus.ScrapeJobStatus

/**
 * Author: @aguestuser
 * Date: 4/3/15
 */

trait ScrapeJobRepo {
  def get(id: Long): Option[ScrapeJobResource]
  def getAll: List[ScrapeJobResource]
  def getIncomplete: List[ScrapeJobResource]
  def create(p: ScrapeJob): Option[Long]
  def edit(id: Long, p: ScrapeJob): Option[Int]
  def delete(id: Long): Option[Int]
}

case class ScrapeJobResource(
                              id: Long,
                              bbl: String,
                              status: ScrapeJobStatus,
                              worker_id: Long)

