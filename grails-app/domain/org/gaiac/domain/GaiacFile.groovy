package org.gaiac.domain

class GaiacFile {

  String name

  String path

  static constraints = {
    name blank:false, unique: true
    path blank:false, unique: false
  }

  static searchable = {
    name boost: 2.0
  }
}
