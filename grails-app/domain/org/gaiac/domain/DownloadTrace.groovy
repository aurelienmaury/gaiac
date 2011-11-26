package org.gaiac.domain

class DownloadTrace {

  GaiacFile file
  Member member

  Date dateCreated

  static constraints = {
    file    nullable: false
    member  nullable: false
  }
}
