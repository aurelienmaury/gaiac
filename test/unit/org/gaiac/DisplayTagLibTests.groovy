package org.gaiac



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.GroovyPageUnitTestMixin} for usage instructions
 */
@TestFor(DisplayTagLib)
class DisplayTagLibTests {

    

    @Test
    void fileSize() {
      def displayTagLib = mockTagLib(DisplayTagLib)
      
      assert displayTagLib.fileSize( [value:50] ) == "50 common.bytes.label"  
      assert displayTagLib.fileSize( [value:1024] ) == "1.0 common.kbytes.label"
      assert displayTagLib.fileSize( [value:1048576], null) == "1.0 common.mbytes.label"
      assert displayTagLib.fileSize( [value:1073741824], null) == "1.0 common.gbytes.label"
      assert displayTagLib.fileSize( [value:1099511627776], null) == "1.0 common.tbytes.label"
      assert displayTagLib.fileSize( [value:1125899906842624], null) == "1.0 common.pbytes.label"
    }

    @Test
    void labelCut() {
      def displayTagLib = mockTagLib(DisplayTagLib)

      assert displayTagLib.labelCut( [max: '5', value: 'aaaaaaaaaa'] ) == 'aa...'
      assert displayTagLib.labelCut( [max: '8', value: 'aaaaaaaaaa'] ) == 'aaaaa...'
    }
}
