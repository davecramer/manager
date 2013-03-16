package com.xtuple

class Organization
{
  String name
  Boolean active
  int connections
  static hasMany = [users:OrgUser]
  static constraints = {
  }
}
