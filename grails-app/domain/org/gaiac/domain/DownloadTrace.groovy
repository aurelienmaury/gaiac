package org.gaiac.domain

class DownloadTrace {

  GaiacFile file
  Member member
  Date downloadDate = new Date()

  static constraints = {
    file    nullable: false
    member  nullable: false
  }
}
