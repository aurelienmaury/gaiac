package org.gaiac

import java.util.concurrent.atomic.AtomicInteger

class DlKeeperService {

  static transactional = false

  def grailsApplication


  def dlPerMemberId = [:]

  boolean startDl(id) {
    createIfNone(id)

    def current = dlPerMemberId."$id".incrementAndGet()
    if (current > maxPerUser()) {
      dlPerMemberId."$id".decrementAndGet()
      return false
    } else {
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
    grailsApplication.config.gaiac.max.dl.per.user
  }

  private void createIfNone(id) {
    if (!dlPerMemberId."$id") {
      dlPerMemberId."$id" = new AtomicInteger()
    }
  }
}
