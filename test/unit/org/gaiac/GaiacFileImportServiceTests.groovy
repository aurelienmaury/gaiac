package org.gaiac



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(GaiacFileImportService)
class GaiacFileImportServiceTests {

    void testMatchingAllowedPatterns() {
        assert service.matchesAnyAllowed("matches.jpg")
        assert service.matchesAnyAllowed("matches.JPg")
        assert service.matchesAnyAllowed("should match.JPG")
        assert !service.matchesAnyAllowed("dontmatch.xml")
    }
}
