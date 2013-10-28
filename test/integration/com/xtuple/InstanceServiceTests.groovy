package com.xtuple

import org.junit.After
import org.junit.Before
import org.junit.Test


class InstanceServiceTests
{

  def instanceService

  @Before
  void setUp() {
    DatabaseServer databaseServer =  new DatabaseServer(host: 'ec2-174-129-105-178.compute-1.amazonaws.com',
            loginUser: 'ubuntu', identity: '/Users/davec/Downloads/dogfood.pem' ).save()

    Organization org = new Organization(name: 'foo',active: true).save()
    databaseServer.addToOrganizations(org)

    def packages = ['git', 'subversion', 'pkg-config', 'postgresql-9.1',
            'postgresql-contrib', 'build-essential', 'libssl-dev',
            'postgresql-server-dev-9.1']

    packages.each {pkg ->
      databaseServer.addToAptPackages(pkg)

    }
    databaseServer.save()


    MobileServer mobileServer =  new MobileServer(host: 'ec2-50-16-90-252.compute-1.amazonaws.com', loginUser: 'ubuntu',
            identity: '/Users/davec/Downloads/ec2keys/ec2-keypair.pem',databaseServer: databaseServer ).save()

    // Setup logic here
  }

  @After
  void tearDown() {
    // Tear down logic here
  }

  @Test
  void testAddInstance()
  {

    MobileServer mobileServer =  MobileServer.findByHost( 'ec2-50-16-90-252.compute-1.amazonaws.com')

    instanceService.addInstance( mobileServer)

  }
  @Test
  void testAddDeploy()
  {

    MobileServer mobileServer =  MobileServer.findByHost( 'ec2-50-16-90-252.compute-1.amazonaws.com')
    instanceService.deploy( mobileServer )
  }
}
