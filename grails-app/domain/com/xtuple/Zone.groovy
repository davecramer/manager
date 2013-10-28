package com.xtuple

class Zone
{
  String name
  String keyName

  NatInstance natInstance
  NginxInstance nginxInstance
  static hasMany = [databaseServers:DatabaseServer,mobileServers:MobileServer]

  static constraints = {
    natInstance nullable: true
    nginxInstance nullable: true
  }
}
