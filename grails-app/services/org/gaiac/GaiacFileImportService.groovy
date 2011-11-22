package org.gaiac

import org.gaiac.domain.GaiacFile
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import java.lang.IllegalArgumentException

class GaiacFileImportService {

  static transactional = false

    def findAllFileToRegister(File startingDir) {

      if (!startingDir.isDirectory()) {
        throw new IllegalArgumentException("Supplied File must be a directory")
      }

      def matchingFiles = []

      startingDir.eachFileRecurse { current ->
        if (current.isFile() && current.canRead() && matchesAnyAllowed(current.getName())) {
          matchingFiles << current
        }
      }

      matchingFiles
    }

    def importFrom(startingPoint) {

      findAllFileToRegister(startingPoint).each { current ->
        def creationParams = [
          name: current.name,
          path: current.absolutePath,
          size: current.size()
        ]
        new GaiacFile(creationParams).save()
      }
    }

    boolean matchesAnyAllowed(String filename) {      
      def res = ConfigurationHolder.config.allowed.extensions.find {
        filename =~ "(?is:.*\\.${it}\$)"
      }
      res
    }
}
