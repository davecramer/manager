package com.xtuple

import groovy.text.GStringTemplateEngine

class HbaConfService {

    def templateEngine = new GStringTemplateEngine()
    def createHbaConf( orgs, MobileServer mobileServer, hbaTemplate) {

      String hbaEntries = ""
      String replicaEntries = ""
      orgs.each { org ->
        hbaEntries += "hostssl ${org.name} +${org.name} 0.0.0.0/32 md5 \n"
      }
      mobileServer.replicas.each { replica ->
        replicaEntries += "host    replication     postgres        ${replica.databaseServer.host}/32        trust\n"
      }
      def binding = [orgs:hbaEntries, replicas:replicaEntries]
      def template = templateEngine.createTemplate(hbaTemplate).make(binding)
      return template.toString()
    }
}
