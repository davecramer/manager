package com.xtuple

class MonitorService
{

  def serverAlive(Server server)
  {
    InetAddress inetAddress = InetAddress.getByName(server.host)
    return inetAddress.isReachable(10000)
  }
}
