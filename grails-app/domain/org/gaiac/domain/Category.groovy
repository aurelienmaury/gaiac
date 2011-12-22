package org.gaiac.domain

class Category {

  String name
  String description

  static constraints = {
    name(
        blank: false,
        unique: true,
        maxSize: 255)

    description(
        unique: false,
        nullable: true,
        maxSize: 1024)
  }

  static mapping = {
    cache true
  }

  static searchable = {
    only = ['name']
    spellCheck 'include'
  }
}
