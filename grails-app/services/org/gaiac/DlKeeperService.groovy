package org.gaiac

import java.util.concurrent.atomic.AtomicInteger
import static org.codehaus.groovy.grails.commons.ConfigurationHolder.config as conf

class DlKeeperService {

  static transactional = false

  def dlPerMemberId = [:]


  boolean startDl(id) {
    createIfNone(id)

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


  void finishDl(id) {
    createIfNone(id)

    def current = dlPerMemberId."$id".decrementAndGet()
    if (current < 0) {
      dlPerMemberId."$id".set(0)
    }
  }


  private int maxPerUser() {
    conf.gaiac.max.dl.per.user
  }


  private void createIfNone(id) {
    if (!dlPerMemberId."$id") {
      dlPerMemberId."$id" = new AtomicInteger()
    }
  }
}
