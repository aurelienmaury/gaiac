dataSource {
    pooled = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
}

hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
    generate_statistics = true
}

environments {

    development {
      dataSource {
        dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
        url = "jdbc:h2:mem:devDb"
      }
    }

    test {
      dataSource {
        dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
        url = "jdbc:h2:mem:devDb"
      }
    }

    preprod {
        dataSource {
          dbCreate = ''
          driverClassName = "com.mysql.jdbc.Driver"
          url = "jdbc:mysql://localhost:8889/gaiac"
          username = "root"
          password = "root"
          pooled = true
          properties {
            maxActive = 20
            maxIdle = 10
            minIdle = 5
            initialSize = 5
            minEvictableIdleTimeMillis = 120000
            timeBetweenEvictionRunsMillis = 120000
            maxWait = 10000
            validationQuery = "SELECT 1"
          }
        }
    }

    production {
        dataSource {
          dbCreate = ''
          driverClassName = "com.mysql.jdbc.Driver"
          url = "jdbc:mysql://localhost/gaiac"
          username = "gaiac"
          password = "fautpasledire"
          pooled = true

          properties {
            maxActive = 20
            maxIdle = 10
            minIdle = 5
            initialSize = 5
            minEvictableIdleTimeMillis = 120000
            timeBetweenEvictionRunsMillis = 120000
            maxWait = 10000
            validationQuery = "SELECT 1"
          }
        }
    }
}
