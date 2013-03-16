package com.xtuple



import grails.test.mixin.*
import org.junit.After
import org.junit.Before
import org.junit.Test


class SshServiceTests
{

  def sshService

  @Before
  void setUp() {
    DatabaseServer databaseServer =  new DatabaseServer(host: 'ec2-50-16-90-252.compute-1.amazonaws.com',
            sudoUser: 'ubuntu', identity: '/Users/davec/Downloads/ec2keys/ec2-keypair.pem' ).save()

    Organization org = new Organization(name: 'foo',active: true).save()
    databaseServer.addToOrganizations(org)
    databaseServer.save()

    MobileServer mobileServer =  new MobileServer(host: 'ec2-50-16-90-252.compute-1.amazonaws.com', sudoUser: 'ubuntu',
            identity: '/Users/davec/Downloads/ec2keys/ec2-keypair.pem',databaseServer: databaseServer ).save()

    // Setup logic here
  }

  @After
  void tearDown() {
    // Tear down logic here
  }

  @Test
  void testCommand()
  {

    DatabaseServer databaseServer =   DatabaseServer.findByHost('ec2-50-16-90-252.compute-1.amazonaws.com')
    if (!sshService.execute(databaseServer, ['ls -al']) )
      throw new Exception("test command failed")
  }
  @Test
  void testMultipleComands()
  {
    DatabaseServer databaseServer =   DatabaseServer.findByHost('ec2-50-16-90-252.compute-1.amazonaws.com')
    if (!sshService.execute(databaseServer, ['cd /usr/local/xtuple','ls -al', 'ls']))
      throw new Exception("test shell failed")

  }

  void xtestConfigureDatabaseServer()
  {

    DatabaseServer databaseServer =   DatabaseServer.findByHost('ec2-50-16-90-252.compute-1.amazonaws.com')

    def packages = ['git', 'subversion', 'pkg-config', 'postgresql-9.1',
            'postgresql-contrib', 'build-essential', 'libssl-dev',
            'postgresql-server-dev-9.1']



    packages.each { pkg ->
      log.debug "Adding package ${pkg}"
      databaseServer.addToAptPackages(pkg)
    }

    if (sshService.installPackages(databaseServer) != 0)
      throw new Exception("configure Database server failed")
  }
  void xtestConfigureMobileServer()
  {

    MobileServer mobileServer =  MobileServer.findByHost('ec2-50-16-90-252.compute-1.amazonaws.com')
    mobileServer.addToAptPackages('git')
    mobileServer.addToAptPackages('pgbouncer')

    if (sshService.installPackages(mobileServer) != 0)
      throw new Exception("configure Mobile server failed")

  }
  @Test
  void testRestartPool()
  {
    MobileServer mobileServer =  MobileServer.findByHost('ec2-50-16-90-252.compute-1.amazonaws.com')

    if ( sshService.restartPool( mobileServer ))
      throw new Exception("restart pool failed")
  }
  void testPutRemoteFile()
  {
    DatabaseServer databaseServer =   DatabaseServer.findByHost('ec2-50-16-90-252.compute-1.amazonaws.com')

    if (sshService.putRemoteFile(databaseServer,'foo',"blah\nblah\nblah") != 0)
      throw new Exception("configure Database server failed")
  }

}
