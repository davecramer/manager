package com.xtuple

abstract class Server
{
  String identity
  String host
  String instanceId
  String ipAddress

  int sshPort

  String loginUser

  static hasMany=[aptPackages:String]


  static constraints = {
    ipAddress nullable: true
  }
   static mapping = {
     tablePerHierarchy false
   }



}
