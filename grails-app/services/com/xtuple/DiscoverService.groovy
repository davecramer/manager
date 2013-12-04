package com.xtuple

class DiscoverService
{
  def sshService

  def iptables(Server server)
  {

    IptablesService iptablesService = new IptablesService(server)

    def chains = iptablesService.getChainNames()
    chains.each {chain->
      def iptables = iptablesService.getRules(chain)
      println iptables

    }
  }
}
