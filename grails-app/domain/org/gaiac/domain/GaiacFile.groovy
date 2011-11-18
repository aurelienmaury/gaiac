package org.gaiac.domain

class GaiacFile {

  String name

  String path

  long size

  Date dateCreated

  Date lastUpdated

  static hasMany = [downloads: DownloadTrace]

  static constraints = {
    name blank:false, unique: true, maxSize: 255
    path blank:false, unique: false, maxSize: 1024
  }

  static mapping = {
    cache true
  }

  static searchable = {
    only = ['name']
  }

  private File concrete() {
    new File(this.path)
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
    }

    topDlLimitBack { limitBack ->
      createAlias 'downloads', 'dls'
      projections {
        groupProperty "id"
        gt 'dls.downloadDate', limitBack
        count 'dls.id', 'dlnb'
      }
      order "dlnb", "desc"
      maxResults(5)
    }

    lastAdded {
      order 'dateCreated', 'desc'
      maxResults(5)
    }
  }
}
