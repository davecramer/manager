package com.xtuple

import com.xtuple.InstanceService
import grails.test.mixin.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(InstanceService)
class InstanceServiceTests
{

  def instanceService
  void testAddInstance()
  {
    DatabaseServer databaseServer = new DatabaseServer(ipAddress: '192.168.1.2',master: Boolean.FALSE).save()
    MobileServer mobileServer =  new MobileServer(ipAddress: '192.168.1.3',databaseServer: databaseServer,master: Boolean.FALSE).save()
    instanceService.addInstance( mobileServer)

  }
}
