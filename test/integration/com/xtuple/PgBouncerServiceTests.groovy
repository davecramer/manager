package com.xtuple

import com.xtuple.PgBouncerService
import grails.test.mixin.*


class PgBouncerServiceTests {

    def pgBouncerService = new PgBouncerService()

    void testCreateInifile() {

        def dbServers = DatabaseServer.findAll()
        def orgs = Organization.findAll()
        dbServers.each { dbServer ->
          pgBouncerService.createIniFile(orgs, dbServer)
        }
    }
}
