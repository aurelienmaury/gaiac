package org.gaiac.domain

class GaiacFile {

  String name

  String path

  int downloaded = 0

  static constraints = {
    name blank:false, unique: true
    path blank:false, unique: false
  }

  static mapping = {
    cache true
  }

  static searchable = {
    name boost: 2.0
  }

  def completeDownload () {
    GaiacFile.executeUpdate("update GaiacFile set downloaded = downloaded+1 where id=:id", [id: this.id])
  }
}
