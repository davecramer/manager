package com.xtuple

class Organization
{
  String name
  Boolean active
  static hasMany = [users:OrgUser]
  static constraints = {
  }
}
