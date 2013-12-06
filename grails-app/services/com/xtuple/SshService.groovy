package com.xtuple

import org.apache.oro.text.regex.MalformedPatternException
import com.jcraft.jsch.*
import expect4j.Closure
import expect4j.Expect4j
import expect4j.ExpectState
import expect4j.matches.Match
import expect4j.matches.RegExpMatch

class SshService
{
  def knownHosts = '/Users/davec/.ssh/known_hosts'

  def executeRemote(Server server, String command)
  {
    JSch jsch = new JSch()

    jsch.addIdentity(server.identity)
    jsch.setKnownHosts(knownHosts)

    def session = jsch.getSession(server.loginUser,server.host,22)
    session.connect()


    Channel channel = session.openChannel('exec')
    ((ChannelExec) channel).setCommand( command )
    ((ChannelExec)channel).setErrStream(System.err)
    channel.setOutputStream(System.out)
    InputStream inputStream = channel.inputStream
    channel.connect()
    def response
    def exitStatus = -1
    byte[] tmp=new byte[1024]
    while(true){

      while(inputStream.available()>0){
        int i=inputStream.read(tmp, 0, 1024)

        if( i<0 ) break
        response = new String(tmp,0,i)
        log.debug(response)
      }
      if(channel.isClosed())
      {
        exitStatus = channel.exitStatus
        if (exitStatus != 0)
        {
          log.error("sudoExec exit status ${exitStatus}, response ${response}")
        }
        System.out.println("exit-status: "+channel.getExitStatus())
        break
      }
      try{Thread.sleep(1000)}catch(Exception ee){}
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

    def session = jsch.getSession(server.loginUser,server.host,22)
    session.connect()


    Channel channel = session.openChannel('exec')

    def getPassword = ""
    def redirection = '>'
    if (append) redirection = '>>'
    def command = "sh -c 'cat ${redirection} ${target}'"
    ((ChannelExec) channel).setCommand( "sudo ${getPassword} ${command}" )


    InputStream inputStream = channel.inputStream
    OutputStream out=channel.outputStream
    ((ChannelExec)channel).setErrStream(errorStream)

    channel.connect()

    log.debug ( "sudoExec ${new String(((ChannelExec)channel).command) }" )


    out.write(contents.bytes)
    out.flush()
    out.close()

    def response
    def exitStatus = -1
    byte[] tmp=new byte[1024]

    while(true){

      while(inputStream.available()>0){
        int i=inputStream.read(tmp, 0, 1024)

        if( i<0 ) break
        response = new String(tmp,0,i)
        log.debug(response)
      }
      if(channel.isClosed())
      {
        exitStatus = channel.exitStatus
        if (exitStatus != 0)
        {
          log.error("sudoExec exit status ${exitStatus}, response ${errorStream}")
        }
        System.out.println("exit-status: "+channel.getExitStatus())
        break
      }
      try{Thread.sleep(1000)}catch(Exception ee){}
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

    log.debug("connecting to ${server.host} as user ${server.loginUser}")

    def session = jsch.getSession(server.loginUser,server.host,22)
    session.connect()

    Channel channel = session.openChannel('exec')

    def getPassword = ""

    ((ChannelExec) channel).setCommand( "sudo ${getPassword} ${command}" )


    InputStream inputStream = channel.inputStream
    OutputStream out=channel.outputStream
    ((ChannelExec)channel).setErrStream(errorStream)

    channel.connect()

    log.debug ( "sudoExec ${new String(((ChannelExec)channel).command) }" )


    def response
    def exitStatus = -1
    byte[] tmp=new byte[1024]

    while(true){

      while(inputStream.available()>0){
        int i=inputStream.read(tmp, 0, 1024)

        if( i<0 ) break
        response = new String(tmp,0,i)
        log.debug(response)
      }
      if(channel.isClosed())
      {
        exitStatus = channel.exitStatus
        if (exitStatus != 0)
        {
          log.error("sudoExec exit status ${exitStatus}, response ${errorStream}")
        }
        log.debug("exit-status: "+channel.getExitStatus())
        break
      }
      try{Thread.sleep(1000)}catch(Exception ee){}
    }

    channel.disconnect()
    session.disconnect()
    return exitStatus
  }
  def execCommand(Session session,  OutputStream errorStream, String command)
  {

    Channel execChannel = session.openChannel('exec')

    def getPassword = ""
    ((ChannelExec) execChannel).setCommand( "sudo ${getPassword} ${command}" )



    InputStream inputStream = execChannel.inputStream
    OutputStream out=execChannel.outputStream
    ((ChannelExec)execChannel).setErrStream(errorStream)

    execChannel.connect()

    log.debug ( "execCommand ${new String(((ChannelExec)execChannel).command) }" )


    def response
    def exitStatus = -1
    byte[] tmp=new byte[1024]

    while(true){

      while(inputStream.available()>0){
        int i=inputStream.read(tmp, 0, 1024)

        if( i<0 ) break
        response = new String(tmp,0,i)
        log.debug(response)
      }
      if(execChannel.isClosed())
      {
        exitStatus = execChannel.exitStatus
        if (exitStatus != 0)
        {
          log.error("execCommand exit status ${exitStatus}, response ${errorStream}")
        }
        log.debug("exit-status: "+execChannel.getExitStatus())
        break
      }
      try{Thread.sleep(1000)}catch(Exception ee){}
    }

    execChannel.disconnect()
    return exitStatus
  }

  def executeSudo(Server server, List <String> commands)
  {
    SshCommandRunner sshCommandRunner = new SshCommandRunner(server, commands)
    sshCommandRunner.run()
    sshCommandRunner.sshResult
  }
  def executeSudoRemote(Server server, List <String> commands)
  {
    ByteArrayOutputStream errorStream

    JSch jsch = new JSch()

    jsch.addIdentity(server.identity)
    jsch.setKnownHosts(knownHosts)

    log.debug("connecting to ${server.host} as user ${server.loginUser}")

    def session = jsch.getSession(server.loginUser,server.host,22)
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
  boolean restartPool(MobileServer mobileServer)
  {
    try {
      return executeSudo(mobileServer, ["service pgbouncer restart"])
    }
    catch (Exception ex )
    {
      log.error( "Unable to add Organization ${ex.message}")
      return false
    }
  }


  private static final int COMMAND_TIMEOUT = -2
  private static String ENTER_CHARACTER = "\n"
  private static final int SSH_PORT = 22
  private static String[] linuxPromptRegEx = ['# ','\\\$ ']



  public SshResult execute(Server server, List<String> cmdsToExecute)
  {

    SshCommandRunner sshCommandRunner = new SshCommandRunner(server, cmdsToExecute)
    sshCommandRunner.run()
    sshCommandRunner.sshResult


  }



}
