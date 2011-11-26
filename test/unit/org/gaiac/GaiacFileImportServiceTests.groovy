package org.gaiac



import grails.test.mixin.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(GaiacFileImportService)
class GaiacFileImportServiceTests {

    void testMatchingAllowedPatterns() {
        assert service.isImportAllowed("matches.jpg")
        assert service.isImportAllowed("matches.JPg")
        assert service.isImportAllowed("should match.JPG")
        assert !service.isImportAllowed("dontmatch.xml")
    }
}
