package com.xtuple

class PgBouncerService
{

  def createIniFile(orgs, dbserver)
  {
    String iniFile = ""
    orgs.each { org ->
       iniFile += "$org.name=host=$org.host port=5432"
    }
  }

}
