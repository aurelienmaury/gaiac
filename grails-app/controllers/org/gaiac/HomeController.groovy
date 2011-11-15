package org.gaiac

import org.gaiac.domain.GaiacFile
import grails.plugins.springsecurity.Secured

@Secured(["hasAnyRole('ROLE_BASIC','ROLE_ADMIN')"])
class HomeController {

    def index() { 

      def idToRetrieve = GaiacFile.topDlAllTime().list().collect { it[0] }

      def topDlList = []
      if (idToRetrieve && idToRetrieve.size() > 0) {
        topDlList = GaiacFile.getAll(idToRetrieve)
      }

      def lastAddedList = GaiacFile.lastAdded().list()

      idToRetrieve = GaiacFile.topDlLimitBack(new Date() - 30).list().collect{ it[0] }
      
      def topDl30List = []
      if (idToRetrieve && idToRetrieve.size() > 0) {
        topDl30List = GaiacFile.getAll(idToRetrieve)
      }
     
      render view:'dashboard', model:[topDlAllTime: topDlList, lastUploads: lastAddedList, topDl30Days: topDl30List]
    }
}
