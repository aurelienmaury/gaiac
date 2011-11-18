package org.gaiac

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

    boolean matchesAnyAllowed(String filename) {      
      def res = ConfigurationHolder.config.allowed.extensions.find {
        filename =~ "(?is:.*\\.${it}\$)"
      }
      res
    }
}
