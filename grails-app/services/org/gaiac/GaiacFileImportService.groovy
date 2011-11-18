package org.gaiac

import org.codehaus.groovy.grails.commons.ConfigurationHolder
//import java.lang.IllegalArgumenException

class GaiacFileImportService {

  static transactional = false

    def findAllFileToRegister(File startingDir) {
/*
      if (!startingDir.isDirectory()) {
        throw new IllegalArgumenException("Supplied File must be a directory")
      }
*/
      def matchingFiles = []

      startingDir.eachFileRecurse { current ->
        if (current.isFile() && matchesAnyAllowed(current.getName())) {
          matchingFiles << current
        }
      }

      matchingFiles
    }

    boolean matchesAnyAllowed(String filename) {      
      ConfigurationHolder.config.gaiacFile.allowed.patterns.find {
        filename =~ "(?is:.*\\.${it}\$)"
      }
    }
}
