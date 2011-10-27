package org.gaiac

import org.gaiac.domain.GaiacFile

class HomeController {

    def index() { 

      def idToRetrieve = GaiacFile.topDlAllTime().list().collect { it[0] }
      def topDlList = GaiacFile.getAll(idToRetrieve)

      def lastAddedList = GaiacFile.lastAdded().list()

      idToRetrieve = GaiacFile.topDlLimitBack(new Date() - 30).list().collect{ it[0] }
      def topDl30List = GaiacFile.getAll(idToRetrieve)

      
      render view:'dashboard', model:[topDlAllTime: topDlList, lastUploads: lastAddedList, topDl30Days: topDl30List]
    }
}
