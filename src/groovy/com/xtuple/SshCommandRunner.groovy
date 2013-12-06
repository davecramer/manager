package com.xtuple

import com.jcraft.jsch.ChannelShell
import com.jcraft.jsch.JSch
import expect4j.Expect4j
import expect4j.ExpectState
import expect4j.matches.Match
import expect4j.matches.RegExpMatch
import org.apache.log4j.Logger
import org.apache.oro.text.regex.MalformedPatternException

/**
 * Created by davec on 13-11-05.
 */
class SshCommandRunner implements Runnable
{

  def knownHosts = '/Users/davec/.ssh/known_hosts'

  Logger log = Logger.getLogger(SshCommandRunner.class)


  public Server server
  public List <String> commands
  SshResult sshResult= new SshResult(success: false,lines: [''])

  private static final int COMMAND_TIMEOUT = -2
  private static String ENTER_CHARACTER = '\n'
  private static final int SSH_PORT = 22
  private static String[] linuxPromptRegEx = ['# ','\\\$ ','~ssh']


  public SshCommandRunner(Server server, List <String> commands)
  {
    this.server=server
    this.commands = commands
  }

  public void run()
  {
    // put sudo in front
    def cmdsToExecute = ['sudo -s','PS1=~ssh'] + commands

    List<Match> lstPattern =  new ArrayList<Match>()

    expect4j.Closure closure = new expect4j.Closure() {
      public void run(ExpectState expectState) throws Exception {

        sshResult.lines.addAll( expectState.buffer.split('\r\n'))
        if ( expectState.match == '# ')
        {
          sshResult.lines.clear()
          lstPattern.clear()
          lstPattern.add new RegExpMatch('PS1=~ssh\r\n~ssh',this)
        }
        else if ( expectState.match == 'PS1=~ssh\r\n~ssh')
        {
          sshResult.lines.clear()
          lstPattern.clear()
          lstPattern.add new RegExpMatch('~ssh',this)

        }
      }
    }



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
      int index =0
      for(String strCmd : cmdsToExecute) {

        sshResult.success = isSuccess(expect, lstPattern, strCmd)


        // I don't really want the motd
        if (index++ == 0)
          sshResult.lines.clear()


      }
      expect.expect(lstPattern)

    } catch (Exception ex) {
      log.error( "Exception executing command", ex)
    } finally {
      closeConnection(expect)
    }
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
