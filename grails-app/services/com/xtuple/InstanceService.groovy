package com.xtuple

import com.xtuple.DatabaseServer
import com.xtuple.Instance
import com.xtuple.MobileServer

class InstanceService
{

  def addInstance(MobileServer mobileServer)
  {
    new Instance( mobileServer ).save()
  }
  def replicateDatabase(DatabaseServer master, DatabaseServer replica )
  {

  }
}
