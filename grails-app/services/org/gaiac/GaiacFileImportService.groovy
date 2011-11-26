package org.gaiac

import org.gaiac.domain.GaiacFile

class GaiacFileImportService {

  static transactional = false

  def grailsApplication

  /**
   *
   * @param startingPoint
   * @return
   */
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

  /**
   *
   * @param filename
   * @return
   */
  boolean isImportAllowed(String filename) {
    def res = grailsApplication.config.allowed.extensions.find {
      filename =~ "(?is:.*\\.${it}\$)"
    }
    res ? true : false
  }

  /**
   *
   * @param startingDir
   * @return
   */
  private def findAllFileToRegister(File startingDir) {
    if (!startingDir) {
      throw new IllegalArgumentException("Param 'startingDir' cannot be null")
    }

    if (!startingDir.isDirectory()) {
      throw new IllegalArgumentException("Param 'startingDir' must target a directory")
    }

    def matchingFiles = []
    startingDir.eachFileRecurse { current ->
      if (current.isFile() && current.canRead() && isImportAllowed(current.getName())) {
        matchingFiles << current
      }
    }
    matchingFiles
  }
}
