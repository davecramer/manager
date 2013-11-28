package com.xtuple

class NatInstance extends Server
{

  static belongsTo = [zone:Zone]
  static hasMany = [natRules:NatRule]


  static constraints = {
  }
}
