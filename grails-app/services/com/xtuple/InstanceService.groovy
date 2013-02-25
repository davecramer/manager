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
  def deploy(MobileServer mobileServer)
  {
    //sshService.addOrganization(mobileServer, organization)
    DatabaseServer databaseServer = mobileServer.databaseServer

    sshService.installPackages(databaseServer)
    def poolIniFile = pgBouncerService.createIniFile( databaseServer.organizations, databaseServer )
    sshService.updatePoolIniFile(mobileServer, poolIniFile)
    def hbaTemplate = new File("./web-app/templates/pg_hba.conf")
    def  hbaConfFile = hbaConfService.createHbaConf(databaseServer.organizations,mobileServer,hbaTemplate)
    sshService.updatePgHbaConf(mobileServer,hbaConfFile)

    sshService.executeSudoRemote(databaseServer, ['mkdir /usr/local/xtuple',
            'pushd /usr/local/xtuple; git clone git://github.com/v8/v8.git; popd',
            'pushd /usr/local/xtuple/v8; git checkout 3.6.2','cd /usr/local/xtuple/v8; make dependencies',
            'pushd /usr/local/xtuple/v8; make library=shared x64.release; popd',
            'pushd /usr/local/xtuple/v8; cp out/x64.release/lib.target/libv8.so /usr/lib/; popd'])

    // do workflow
    // pgbouncer.ini file userlist.txt
    // pg_hba.conf
    // config.js
    // install all software on it
    // node, V8 on db plv8js on db
  }

}
