package org.gaiac

import grails.test.mixin.*
import org.junit.*
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import java.util.concurrent.atomic.AtomicInteger

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(DlKeeperService)
class DlKeeperServiceTests {

  def myAccountId = 1

  @Before
  void reset() {
    service.dlPerMemberId = [:]
    ConfigurationHolder.config.gaiac.max.dl.per.user=2
  }

  @Test
  void dlStartIncrementsCounter() {

    assertTrue(service.startDl(myAccountId))

    assert service.dlPerMemberId."$myAccountId" != null
    assert service.dlPerMemberId."$myAccountId".get() == 1
  }

  @Test
  void dlFinishDecrementsCounter() {

    assertTrue(service.startDl(myAccountId))

    service.finishDl(myAccountId)

    assert service.dlPerMemberId."$myAccountId" != null
    assert service.dlPerMemberId."$myAccountId".get() == 0
  }

  @Test
  void dlFinishStaysPositive() {

    service.finishDl(myAccountId)

    assert service.dlPerMemberId."$myAccountId" != null
    assert service.dlPerMemberId."$myAccountId".get() == 0
  }

  @Test
  void dlStartHitsRoof() {

    assert service.startDl(myAccountId)
    assert service.startDl(myAccountId)
    
    assert !service.startDl(myAccountId)

    assert service.dlPerMemberId."$myAccountId" != null
    assert service.dlPerMemberId."$myAccountId".get() == 2

  }
}
