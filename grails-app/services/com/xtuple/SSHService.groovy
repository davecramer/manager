package com.xtuple

import com.jcraft.jsch.*

class SshService
{
  def knownHosts = '/Users/davec/.ssh/known_hosts'

  def executeRemote(Server server, String command)
  {
    JSch jsch = new JSch()

    jsch.addIdentity(server.identity)
    jsch.setKnownHosts(knownHosts)

    def session = jsch.getSession(server.sudoUser,server.host,22)
    session.connect()


    Channel channel = session.openChannel('exec')
    ((ChannelExec) channel).setCommand( command )
    ((ChannelExec)channel).setErrStream(System.err)
    channel.setOutputStream(System.out)
    InputStream inputStream = channel.inputStream
    channel.connect()
    def response
    def exitStatus = -1
    byte[] tmp=new byte[1024];
    while(true){

      while(inputStream.available()>0){
        int i=inputStream.read(tmp, 0, 1024);

        if( i<0 ) break;
        response = new String(tmp,0,i)
        log.debug(response);
      }
      if(channel.isClosed())
      {
        exitStatus = channel.exitStatus
        if (exitStatus != 0)
        {
          log.error("sudoExec exit status ${exitStatus}, response ${response}")
        }
        System.out.println("exit-status: "+channel.getExitStatus());
        break;
      }
      try{Thread.sleep(1000);}catch(Exception ee){}
    }

    channel.disconnect()
    session.disconnect()
    return exitStatus

  }
  def putRemoteFile(Server server, String target, String contents)
  {
    ByteArrayOutputStream errorStream =  new ByteArrayOutputStream()

    JSch jsch = new JSch()

    jsch.addIdentity(server.identity)
    jsch.setKnownHosts(knownHosts)

    def session = jsch.getSession(server.sudoUser,server.host,22)
    session.connect()


    Channel channel = session.openChannel('exec')

    def getPassword = ""
    if (server?.sudoPass)
    {
      getPassword = "-S -p '' "
    }
    def command = "cat > ${target}"
    ((ChannelExec) channel).setCommand( "sudo ${getPassword} ${command}" )


    InputStream inputStream = channel.inputStream
    OutputStream out=channel.outputStream
    ((ChannelExec)channel).setErrStream(errorStream);

    channel.connect();

    log.debug ( "sudoExec ${new String(((ChannelExec)channel).command) }" )

    if (server?.sudoPass)
    {
      out.write((server.sudoPass+"\n").getBytes())
      out.flush();
    }

    out.write(contents.bytes)
    out.flush()
    out.close()

    def response
    def exitStatus = -1
    byte[] tmp=new byte[1024];

    while(true){

      while(inputStream.available()>0){
        int i=inputStream.read(tmp, 0, 1024);

        if( i<0 ) break;
        response = new String(tmp,0,i)
        log.debug(response);
      }
      if(channel.isClosed())
      {
        exitStatus = channel.exitStatus
        if (exitStatus != 0)
        {
          log.error("sudoExec exit status ${exitStatus}, response ${errorStream}")
        }
        System.out.println("exit-status: "+channel.getExitStatus());
        break;
      }
      try{Thread.sleep(1000);}catch(Exception ee){}
    }

    channel.disconnect()
    session.disconnect()
    return exitStatus

  }
  def executeSudoRemote(Server server, String command)
  {
    ByteArrayOutputStream errorStream =  new ByteArrayOutputStream()

    JSch jsch = new JSch()

    jsch.addIdentity(server.identity)
    jsch.setKnownHosts(knownHosts)

    def session = jsch.getSession(server.sudoUser,server.host,22)
    session.connect()


    Channel channel = session.openChannel('exec')

    def getPassword = ""
    if (server?.sudoPass)
    {
      getPassword = "-S -p '' "
    }
    ((ChannelExec) channel).setCommand( "sudo ${getPassword} ${command}" )


    InputStream inputStream = channel.inputStream
    OutputStream out=channel.outputStream
    ((ChannelExec)channel).setErrStream(errorStream);

    channel.connect();

    log.debug ( "sudoExec ${new String(((ChannelExec)channel).command) }" )

    if (server?.sudoPass)
    {
      out.write((server.sudoPass+"\n").getBytes())
      out.flush();
    }

    def response
    def exitStatus = -1
    byte[] tmp=new byte[1024];

    while(true){

      while(inputStream.available()>0){
        int i=inputStream.read(tmp, 0, 1024);

        if( i<0 ) break;
        response = new String(tmp,0,i)
        log.debug(response);
      }
      if(channel.isClosed())
      {
        exitStatus = channel.exitStatus
        if (exitStatus != 0)
        {
          log.error("sudoExec exit status ${exitStatus}, response ${errorStream}")
        }
        System.out.println("exit-status: "+channel.getExitStatus());
        break;
      }
      try{Thread.sleep(1000);}catch(Exception ee){}
    }

    channel.disconnect()
    session.disconnect()
    return exitStatus
  }

  def addOrganization(MobileServer mobileServer, Organization organization)
  {
    def command = "${organization.name}=host=${mobileServer.databaseServer.host} port=5432 dbname=${organization.name}"

    try {
      return executeSudoRemote(mobileServer, "echo ${command} >> /etc/pgbouncer.ini")
    }
    catch (Exception ex )
    {
      log.error( "Unable to add Organization ${ex.message}")
      return false
    }
  }
  def updatePoolIniFile(MobileServer mobileServer, String poolIniFile )
  {
    return putRemoteFile(mobileServer,'/etc/pgbouncer/pgbouncer.ini', poolIniFile )
  }
  def updatePgHbaConf(MobileServer mobileServer, String pgHbaConf)
  {
    return putRemoteFile(mobileServer.databaseServer,'/etc/postgresql/9.1/main/pg_hba.conf', pgHbaConf)
  }

  def installPackages(Server server)
  {

    try {


    log.debug "apt-get update"
    executeSudoRemote(server,'apt-get -y update')

    def command = 'apt-get -y install '

    server.aptPackages.each { pkg ->
      command += "${pkg} "
    }
    log.debug "command ${command}"
    return executeSudoRemote(server,command)
    }
    catch (Exception ex )
    {
      log.error "unable to configure Database server ${ex.message}"
      return -1
    }
    // put v8 on, put plv8 on
  }
  def restartPool(MobileServer mobileServer)
  {
    try {
      return executeSudoRemote(mobileServer, "service pgbouncer restart")
    }
    catch (Exception ex )
    {
      log.error( "Unable to add Organization ${ex.message}")
      return -1
    }
  }
}
