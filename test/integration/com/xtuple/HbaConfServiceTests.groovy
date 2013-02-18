package com.xtuple

import com.xtuple.HbaConfService
import grails.test.mixin.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */

class HbaConfServiceTests {
    def hbaConfService = new HbaConfService()
    def template = new File("./web-app/templates/pg_hba.conf")

    void testCreateConfFile() {
        def orgs = Organization.findAllByActive(true)
        def servers = MobileServer.findAllByReplica(false)
        servers.each {mobileServer ->
          def conf = hbaConfService.createHbaConf(orgs, mobileServer, template)
          print conf
        }
    }
}
