package org.gaiac

import java.util.concurrent.atomic.AtomicInteger

class DlKeeperService {

  static transactional = false

  def grailsApplication

  def dlPerMemberId = [:]

  /**
   *
   * @param id
   * @return
   */
  boolean startDl(id) {
    createIfNone(id)
    log.debug "TEST"
    def current = dlPerMemberId."$id".incrementAndGet()

    if (current > maxPerUser()) {
      dlPerMemberId."$id".decrementAndGet()
      log.debug "current: ${current} > max: ${maxPerUser()}"
      return false
    } else {
      log.debug "current: ${current} <= max: ${maxPerUser()}"
      return true
    }
  }

  /**
   *
   * @param id
   */
  void finishDl(id) {
    createIfNone(id)

    def current = dlPerMemberId."$id".decrementAndGet()
    if (current < 0) {
      dlPerMemberId."$id".set(0)
    }
  }

  /**
   *
   * @return
   */
  private int maxPerUser() {
    grailsApplication.config.gaiac.max.dl.per.user as int
  }

  /**
   *
   * @param id
   */
  private void createIfNone(id) {
    if (!dlPerMemberId."$id") {
      dlPerMemberId."$id" = new AtomicInteger()
    }
  }
}
