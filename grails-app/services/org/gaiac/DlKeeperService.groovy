package org.gaiac

import java.util.concurrent.atomic.AtomicInteger
import org.codehaus.groovy.grails.commons.ConfigurationHolder

class DlKeeperService {

  static transactional = false

  def dlPerMemberId = [:]

  boolean startDl(id) {
    createIfNone(id)

    def current = dlPerMemberId."$id".incrementAndGet()

    if (current > maxPerUser()) {
      dlPerMemberId."$id".decrementAndGet()
      println "current: ${current} > max: ${maxPerUser()}"
      return false
    } else {
      println "current: ${current} <= max: ${maxPerUser()}"
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
    ConfigurationHolder.config.gaiac.max.dl.per.user
  }

  private void createIfNone(id) {
    if (!dlPerMemberId."$id") {
      dlPerMemberId."$id" = new AtomicInteger()
    }
  }
}
