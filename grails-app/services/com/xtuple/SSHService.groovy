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

  def putRemoteFile(Server server, String target, String contents, Boolean append=true)
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
    def redirection = '>'
    if (append) redirection = '>>'
    def command = "sh -c 'cat ${redirection} ${target}'"
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

    log.debug("connecting to ${server.host} as user ${server.sudoUser}")

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
        log.debug("exit-status: "+channel.getExitStatus());
        break;
      }
      try{Thread.sleep(1000);}catch(Exception ee){}
    }

    channel.disconnect()
    session.disconnect()
    return exitStatus
  }
  def execCommand(Session session, String sudoPass, OutputStream errorStream, String command)
  {

    Channel execChannel = session.openChannel('exec')

    def getPassword = ""
    if (sudoPass != null)
    {
      getPassword = "-S -p '' "
    }
    ((ChannelExec) execChannel).setCommand( "sudo ${getPassword} ${command}" )



    InputStream inputStream = execChannel.inputStream
    OutputStream out=execChannel.outputStream
    ((ChannelExec)execChannel).setErrStream(errorStream);

    execChannel.connect();

    log.debug ( "execCommand ${new String(((ChannelExec)execChannel).command) }" )


    if (sudoPass!=null)
    {
      out.write((sudoPass+"\n").getBytes())
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
      if(execChannel.isClosed())
      {
        exitStatus = execChannel.exitStatus
        if (exitStatus != 0)
        {
          log.error("execCommand exit status ${exitStatus}, response ${errorStream}")
        }
        log.debug("exit-status: "+execChannel.getExitStatus());
        break;
      }
      try{Thread.sleep(1000);}catch(Exception ee){}
    }

    execChannel.disconnect()
    return exitStatus
  }

  def executeSudoRemote(Server server, List <String> commands)
  {
    ByteArrayOutputStream errorStream

    JSch jsch = new JSch()

    jsch.addIdentity(server.identity)
    jsch.setKnownHosts(knownHosts)

    log.debug("connecting to ${server.host} as user ${server.sudoUser}")

    def session = jsch.getSession(server.sudoUser,server.host,22)
    session.connect()


    Channel channel = session.openChannel('shell')

    PrintStream printStream = new PrintStream( channel.outputStream )
    channel.connect()

    printStream.println('sudo -s')
    printStream.flush()

    printStream.println('id -un')
    printStream.flush()

    BufferedReader reader = new BufferedReader(new InputStreamReader(channel.inputStream))

    boolean foundPrompt = false
    def line
    while (!foundPrompt)
    {
      if ( !reader.ready() ) sleep(100)

      line = reader.readLine()
      log.debug "read -> ${line}"

      if (line == 'root') foundPrompt = true
    }
    log.debug "got here"
    def prompt = readToChar(reader, (char)'#',1000 )

    commands.each{ cmd->
      foundPrompt = false
      printStream.println(cmd)
      printStream.flush()

      while(!foundPrompt){
        if ( !reader.ready() ) sleep(100)

        line = reader.readLine()
        log.debug "read -> ${line}"

        if (line == prompt) foundPrompt = true
      }

    }



    channel.disconnect()
    session.disconnect()
    return 0
  }
  private String readToChar(Reader reader, char ch, long timeout)
  {
    StringBuffer stringBuffer=new StringBuffer()
    long currentTime = System.currentTimeMillis()


    while( true )
    {

      // keep reading as long as there is data
      while (reader.ready() )
      {
         def charRead = reader.read()
         // check for end of file or the char we are looking for
         if ( charRead == -1 )
         {
           return stringBuffer.toString()
         }
         else
         {
           // move the timeout ahead on each char
           currentTime = System.currentTimeMillis()
           stringBuffer.append((char)charRead)
           // we want the last char as well
           if (charRead == ch ) return stringBuffer.toString()
         }
      }
      // nothing ready so sleep for a bit
      sleep(10)
      // if we end up expiring return
      if (System.currentTimeMillis() > currentTime + timeout)
      {
        return stringBuffer.toString()
      }

    }
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
    return putRemoteFile(mobileServer,'/etc/pgbouncer/pgbouncer.ini', poolIniFile, true )
  }
  def updatePgHbaConf(MobileServer mobileServer, String pgHbaConf)
  {
    // replace the file do not append
    return putRemoteFile(mobileServer.databaseServer,'/etc/postgresql/9.1/main/pg_hba.conf', pgHbaConf,false)
  }

  def installPackages(Server server)
  {

    try {


    log.debug "apt-get -y update"
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
