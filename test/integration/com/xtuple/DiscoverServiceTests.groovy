package com.xtuple

import org.junit.Test

/**
 * Created by davec on 13-11-25.
 */
class DiscoverServiceTests
{
  def discoverService

  @Test
  public void testGetRules()
  {
    DatabaseServer databaseServer =   DatabaseServer.findByHost('ec2-54-208-56-185.compute-1.amazonaws.com')
    def rules = discoverService.iptables(databaseServer)

    println rules

  }
}
