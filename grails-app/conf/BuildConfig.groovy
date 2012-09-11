grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.war.file = "target/${appName}.war"

grails.project.dependency.resolution = {
  // inherit Grails' default dependencies
  inherits("global") {
    // uncomment to disable ehcache
    // excludes 'ehcache'
  }
  log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
  checksums true // Whether to verify checksums on resolve

  repositories {
    inherits true // Whether to inherit repository definitions from plugins
    grailsPlugins()
    grailsHome()
    grailsCentral()
    mavenCentral()
    mavenLocal()
  }
  dependencies {
    runtime 'mysql:mysql-connector-java:5.1.18'
  }

  plugins {
    compile ":hibernate:$grailsVersion"
    compile ":jquery:1.7.2"
    compile ":resources:1.1.6"
    compile ':jquery:1.7.1'
    compile ':mail:1.0'
    compile ':searchable:0.6.3'
    compile ':database-migration:1.0'

    compile ':jmx:0.7'
    compile ':spring-security-core:1.2.7.3'

    build ":tomcat:$grailsVersion"
  }
}
