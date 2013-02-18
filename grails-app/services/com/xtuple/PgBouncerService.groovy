package com.xtuple

class PgBouncerService
{

  /*
  add orgs to the pool
  dbserver is the target database server
   */
  def createIniFile(orgs, DatabaseServer dbserver)
  {
    String iniFile = ""
    orgs.each { org ->
       iniFile += "$org.name=host=$dbserver.host port=5432\n"
    }
    return iniFile
  }

}
