package org.gaiac.domain

class GaiacFile {

  String name

  String path

  String exturl

  long size

  long downloadNumber

  Date dateCreated

  Date lastUpdated

  static belongsTo = Category
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

    exturl(
        blank: true,
        url: true,
        maxSize: 1024,
    nullable: true)
  }

  static mapping = {
    cache true
  }

  static searchable = {
    only = ['name', 'categories']
    categories(component: [maxDepth: 1])
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

    findByCategoryIdIn { catIdList ->
      categories {
        'id' in catIdList
      }
      cache true
    }

    findAllByIdInAndCategoryIdIn { idList, catIdList ->
      'id' in idList
      categories {
        'id' in catIdList
      }
      cache true
    }

    lastAdded {
      order 'dateCreated', 'desc'
      maxResults(5)
      cache true
    }
  }
}
