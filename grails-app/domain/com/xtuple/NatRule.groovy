package com.xtuple

class NatRule
{
  String protocol
  String destIP
  int dport
  String toIP
  int toPort
  Instance instance


  static constraints = {
    instance nullable:true
  }
  String toString()
  {
    "DNAT $protocol $destIP $toIP:$toIP"
  }
}
