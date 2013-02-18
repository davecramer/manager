package com.xtuple

import org.junit.After
import org.junit.Before
import org.junit.Test


class InstanceServiceTests
{

  def instanceService

  @Before
  void setUp() {
    DatabaseServer databaseServer =  new DatabaseServer(host: 'ec2-50-16-90-252.compute-1.amazonaws.com',
            sudoUser: 'ubuntu', identity: '/Users/davec/Downloads/ec2keys/ec2-keypair.pem' ).save()

    Organization org = new Organization(name: 'foo',active: true).save()
    databaseServer.addToOrganizations(org)
    databaseServer.save()

    MobileServer mobileServer =  new MobileServer(host: 'ec2-50-16-90-252.compute-1.amazonaws.com', sudoUser: 'ubuntu',
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
}
