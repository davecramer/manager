package com.xtuple

class InstanceService
{
  def sshService
  def pgBouncerService
  def hbaConfService

  def addInstance(MobileServer mobileServer)
  {
    new Instance( mobileServer ).save()
  }
  def replicateDatabase(DatabaseServer master, DatabaseServer replica )
  {

  }
  def addOrganization(MobileServer mobileServer, Organization organization)
  {
    //sshService.addOrganization(mobileServer, organization)
    DatabaseServer databaseServer = mobileServer.databaseServer

    sshService.installPackages(databaseServer)
    def poolIniFile = pgBouncerService.createIniFile( databaseServer.organizations, databaseServer )
    sshService.updatePoolIniFile(mobileServer, poolIniFile)
    def hbaTemplate = new File("./web-app/templates/pg_hba.conf")
    def  hbaConfFile = hbaConfService.createHbaConf(databaseServer.organizations,mobileServer,hbaTemplate)
    sshService.updatePgHbaConf(mobileServer,hbaConfFile)

    // do workflow
    // pgbouncer.ini file userlist.txt
    // pg_hba.conf
    // config.js
    // install all software on it
    // node, V8 on db plv8js on db
  }

}
