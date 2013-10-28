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
    ((ChannelExec)channel).setErrStream(errorStream)

    channel.connect()

    log.debug ( "sudoExec ${new String(((ChannelExec)channel).command) }" )

    if (server?.sudoPass)
    {
      out.write((server.sudoPass+"\n").getBytes())
      out.flush()
    }

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
    if (server?.sudoPass)
    {
      getPassword = "-S -p '' "
    }
    ((ChannelExec) channel).setCommand( "sudo ${getPassword} ${command}" )


    InputStream inputStream = channel.inputStream
    OutputStream out=channel.outputStream
    ((ChannelExec)channel).setErrStream(errorStream)

    channel.connect()

    log.debug ( "sudoExec ${new String(((ChannelExec)channel).command) }" )

    if (server?.sudoPass)
    {
      out.write((server.sudoPass+"\n").getBytes())
      out.flush()
    }

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
    ((ChannelExec)execChannel).setErrStream(errorStream)

    execChannel.connect()

    log.debug ( "execCommand ${new String(((ChannelExec)execChannel).command) }" )


    if (sudoPass!=null)
    {
      out.write((sudoPass+"\n").getBytes())
      out.flush()
    }

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
  private static String[] linuxPromptRegEx = ['~#','~\\\$']
  def buffer = ""



  public boolean execute(Server server, List<String> cmdsToExecute) {

    buffer =  ""
    Closure closure = new Closure() {
      public void run(ExpectState expectState) throws Exception {
        buffer += expectState.getBuffer()
      }
    }
    List<Match> lstPattern =  new ArrayList<Match>()

    for (String regexElement : linuxPromptRegEx) {
      try {
        Match mat = new RegExpMatch(regexElement, closure)

        lstPattern.add(mat)
      } catch (MalformedPatternException e) {
        e.printStackTrace()
      } catch(Exception e) {
        e.printStackTrace()
      }
    }

    Expect4j expect
    boolean ret = false
    try {
      expect = getExpect4j(server)
      for(String strCmd : cmdsToExecute) {
        ret = isSuccess(expect, lstPattern, strCmd)

      }

      ret = expect.expect(lstPattern) > 0
    } catch (Exception ex) {
      log.error( "Exception executing command", ex)
    } finally {
      closeConnection(expect)
    }

    log.debug buffer.toString()
    return ret
  }

  public boolean executeSudo(Server server, List <String> listCmds)
  {

    // put sudo in front
    def cmdsToExecute = ['sudo -s'] + listCmds

    buffer =  ""
    Closure closure = new Closure() {
      public void run(ExpectState expectState) throws Exception {
        buffer += expectState.getBuffer()
      }
    }
    List<Match> lstPattern =  new ArrayList<Match>()

    for (String regexElement : linuxPromptRegEx) {
      try {
        Match mat = new RegExpMatch(regexElement, closure)

        lstPattern.add(mat)
      } catch (MalformedPatternException e) {
        e.printStackTrace()
      } catch(Exception e) {
        e.printStackTrace()
      }
    }

    Expect4j expect
    boolean ret = false

    try {
      expect = getExpect4j(server)
      for(String strCmd : cmdsToExecute) {
        ret = isSuccess(expect, lstPattern, strCmd)
      }

      // did we match something
      ret = expect.expect(lstPattern) > 0
    } catch (Exception ex) {
      log.error( "Exception executing command", ex)
    } finally {
      closeConnection(expect)
    }

    return ret
  }

  /**
   *
   * @param objPattern
   * @param strCommandPattern
   * @return
   */
  private boolean isSuccess(Expect4j expect, List<Match> objPattern,String strCommandPattern)
  {
    try
    {


      // did we timeout before
      if (expect.expect(objPattern) != COMMAND_TIMEOUT) {
        expect.send(strCommandPattern)
        expect.send(ENTER_CHARACTER)
        return true
      }

      return false
    } catch (MalformedPatternException ex) {
      ex.printStackTrace()
      return false
    } catch (Exception ex) {
      ex.printStackTrace()
      return false
    }
  }
  /**
   *
   * @param hostname
   * @param username
   * @param password
   * @param port
   * @return
   * @throws Exception
   */
  private Expect4j getExpect4j(Server server) throws Exception

  {
    JSch jsch = new JSch()

    jsch.addIdentity(server.identity)
    jsch.setKnownHosts(knownHosts)

    log.debug("connecting to ${server.host} as user ${server.loginUser}")

    def session = jsch.getSession(server.loginUser,server.host,22)


    if (server.sudoPass != null)
    {
      session.setPassword(server.sudoPass)
    }

    Hashtable<String,String> config = new Hashtable<String,String>()
    config.put("StrictHostKeyChecking", "no")
    session.setConfig(config)
    session.connect(60000)


    ChannelShell channel = (ChannelShell) session.openChannel("shell")
    Expect4j expect = new Expect4j(channel.getInputStream(), channel.getOutputStream())
    channel.connect()
    return expect
  }
  /**
   *
   * @param intRetVal
   * @return
   */
  private boolean checkResult(int intRetVal) {
    // this is really a timeout return
    return intRetVal == COMMAND_TIMEOUT

  }
  /**
   *
   */
  private void closeConnection(Expect4j expect) {
    if (expect!=null) {
      expect.close()
    }
  }

}
