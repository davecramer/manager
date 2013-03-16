package com.xtuple

import com.xtuple.PgBouncerService
import grails.test.mixin.*
import org.junit.After
import org.junit.Before
import org.junit.Test


class PgBouncerServiceTests {

    def pgBouncerService = new PgBouncerService()

  @Test
    void testCreateInifile() {

        def dbServers = DatabaseServer.findAll()
        def orgs = Organization.findAll()
        dbServers.each { dbServer ->

          def iniFile =  pgBouncerService.createIniFile(orgs, dbServer)
          printf iniFile
        }
    }
}
