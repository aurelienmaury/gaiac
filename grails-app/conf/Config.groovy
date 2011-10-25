// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

grails.config.locations = []

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

def envConfigKey = 'GAIAC_CONFIG'
if (System.getenv(envConfigKey)) {
  println "External configuration defined: ${System.getenv(envConfigKey)}"
  grails.config.locations << "file:" + System.getenv(envConfigKey)
} else {
  println "No external configuration defined. Applying defaults."
}


grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text/plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

grails.resources.modules = {
  core {
  dependsOn 'jquery'
    resource url:'/css/bootstrap.css'
  resource url:'/js/bootstrap-alerts.js'
  resource url:'/js/bootstrap-dropdown.js'
  }
}
// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// set per-environment serverURL stem for creating absolute links
environments {
    development {
        grails.logging.jul.usebridge = true
    }
    /*production {
        grails.logging.jul.usebridge = false
        grails.serverURL = "http://www.changeme.com"
    }*/
}

gaiac.log.pattern = '%-5p [%c{2}] %m%n'
// log4j configuration
log4j = {
    appenders {
        console name:'stdout', layout:pattern(conversionPattern: gaiac.log.pattern)
    }

    error 'org.codehaus.groovy.grails.web.servlet',  //  controllers
          'org.codehaus.groovy.grails.web.pages', //  GSP
          'org.codehaus.groovy.grails.web.sitemesh', //  layouts
          'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
          'org.codehaus.groovy.grails.web.mapping', // URL mapping
          'org.codehaus.groovy.grails.commons', // core / classloading
          'org.codehaus.groovy.grails.plugins', // plugins
          'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
          'org.springframework',
          'org.hibernate',
          'net.sf.ehcache.hibernate'

    debug 'grails.app.controllers'
}

// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'org.gaiac.domain.Member'
grails.plugins.springsecurity.userLookup.usernamePropertyName = 'email'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'org.gaiac.domain.MemberRole'
grails.plugins.springsecurity.authority.className = 'org.gaiac.domain.Role'

gaiac.repository.path="/tmp"
gaiac.max.dl.per.user=4

environments {
  preprod {
    grails.plugin.databasemigration.updateOnStart=true
    grails.plugin.databasemigration.updateOnStartFileNames=['changelog.groovy']
  }

  production {
    grails.plugin.databasemigration.updateOnStart=true
    grails.plugin.databasemigration.updateOnStartFileNames=['changelog.groovy']
  }
}
