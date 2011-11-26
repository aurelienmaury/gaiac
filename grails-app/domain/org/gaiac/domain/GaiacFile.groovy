package org.gaiac.domain

class GaiacFile {

  String name

  String path

  long size

  long downloadNumber

  Date dateCreated

  Date lastUpdated

  static hasMany = [
      downloads: DownloadTrace,
      categories: Category
  ]

  static constraints = {
    name(
        blank: false,
        unique: true,
        maxSize: 255)

    path(
        blank: false,
        unique: false,
        maxSize: 1024)
  }

  static mapping = {
    cache true
  }

  static searchable = {
    only = ['name']
  }

  static namedQueries = {
    topDlAllTime {
      createAlias 'downloads', 'dls'
      projections {
        groupProperty "id"
        count 'dls.id', 'dlnb'
      }
      order "dlnb", "desc"
      maxResults(5)
      cache true
    }

    topDlLimitBack { limitBack ->
      createAlias 'downloads', 'dls'
      projections {
        groupProperty "id"
        gt 'dls.dateCreated', limitBack
        count 'dls.id', 'dlnb'
      }
      order "dlnb", "desc"
      maxResults(5)
      cache true
    }

    lastAdded {
      order 'dateCreated', 'desc'
      maxResults(5)
      cache true
    }
  }
}
