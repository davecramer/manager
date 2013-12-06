package com.xtuple

abstract class Server
{
  String identity
  String host
  String instanceId

  int sshPort

  String loginUser
  String sudoPass=null

  static hasMany=[aptPackages:String]


  static constraints = {
    sudoPass nullable: true
  }
   static mapping = {
     tablePerHierarchy false
   }



}
