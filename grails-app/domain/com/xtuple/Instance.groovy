package com.xtuple

class Instance
{
  MobileServer mobileServer
  Boolean standby
  static hasMany = [replicas: MobileServer ]

  static constraints = {
  }

  public Instance(mobileServer)
  {
    this.mobileServer = mobileServer
  }
}
