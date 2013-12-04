package com.xtuple

class Zone
{
  String name
  String keyName

  NatInstance natInstance
  NginxInstance nginxInstance
  static hasMany = [databaseServers:DatabaseServer,mobileServers:MobileServer,organizations:Organization]

  static constraints = {
    natInstance nullable: true
    nginxInstance nullable: true
  }
  public String toString()
  {
    name
  }
}
