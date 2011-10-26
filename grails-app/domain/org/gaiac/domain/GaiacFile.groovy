package org.gaiac.domain

class GaiacFile {

  String name

  String path

  long size

  Date dateCreated

  Date lastUpdated

  static hasMany = [downloads: DownloadTrace]

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

  private File concrete() {
    new File(this.path)
  }
}
