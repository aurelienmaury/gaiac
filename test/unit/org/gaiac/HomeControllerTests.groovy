package org.gaiac


import grails.test.mixin.*
import org.junit.*
import org.gaiac.domain.GaiacFile
import org.gaiac.domain.DownloadTrace
import grails.test.mixin.domain.DomainClassUnitTestMixin

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(HomeController)
@Mock([DownloadTrace, GaiacFile])
class HomeControllerTests {

    void testIndex() {
      
      def namedQueries = mockFor(GaiacFile, true)

      namedQueries.demand.static.topDlAllTime {
        { ->
          def list = {
            [ [1], [2] ]
          }
        }
      }

      namedQueries.demand.static.topDlLimitBack {
        { ->
          def list = {
            [ [1], [2] ]
          }
        }
      }

      

      controller.index()

      assert view == "/home/dashboard"
    }
}