package com.xtuple

class DatabaseServer extends Server
{
  Boolean master=false
  static hasMany = [organizations:Organization]
  static belongsTo = [zone:Zone]


  static constraints = {
  }

}
