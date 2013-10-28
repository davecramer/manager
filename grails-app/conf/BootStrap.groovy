import com.xtuple.DatabaseServer
import com.xtuple.MobileServer
import com.xtuple.NatInstance
import com.xtuple.NginxInstance
import com.xtuple.Organization
import com.xtuple.Zone

class BootStrap {

    def init = { servletContext ->


      def organization = new Organization(name: 'testorg',active: true)

      def zone = new Zone(name: 'US East', keyName:'/Users/davec/Downloads/ec2keys/ec2-keypair.pem' )
      if ( !zone.save())
        zone.errors.each{
          log.debug it
        }

      def natInstance = new NatInstance(instanceId: 'i-1234', name: 'ec2east', loginUser: 'ec2user', sshPort: 22, host: 'ec2east.xtuple.com', identity: '/Users/davec/Downloads/ec2keys/ec2-keypair.pem',
      zone:zone)
      if (!natInstance.save())
        natInstance.errors.each{
          log.debug it
        }
      zone.natInstance = natInstance
      zone.save()

      def nginxInstance = new NginxInstance(instanceId: 'i-1234',name: 'ec2east', loginUser: 'ubuntu', sshPort: 10022, host: 'ec2east.xtuple.com', identity: '/Users/davec/Downloads/ec2keys/ec2-keypair.pem',
      zone:zone)
      if (!nginxInstance.save())
        nginxInstance.errors.each{
          log.debug it
        }

      zone.nginxInstance = nginxInstance
      zone.save()


      def databaseServer = new DatabaseServer(instanceId: 'i-1234',host: 'ec2-50-16-90-251.compute-1.amazonaws.com', loginUser: 'ubuntu', identity: '/Users/davec/Downloads/ec2keys/ec2-keypair.pem' ,
      zone:zone)
      if (!databaseServer.save())
        databaseServer.errors.each {
          log.debug it
        }

      zone.addToDatabaseServers(databaseServer)
      zone.save()

      databaseServer = new DatabaseServer(instanceId: 'i-1234',host: 'ec2-50-16-90-250.compute-1.amazonaws.com', loginUser: 'ubuntu', identity: '/Users/davec/Downloads/ec2keys/ec2-keypair.pem',zone:zone )
      if (!databaseServer.save())
        databaseServer.errors.each {
          log.debug it
        }

      zone.addToDatabaseServers(databaseServer)
      zone.save()

      def masterServer = new MobileServer(instanceId: 'i-1234',host: 'ec2-50-16-90-252.compute-1.amazonaws.com', loginUser: 'ubuntu', identity: '/Users/davec/Downloads/ec2keys/ec2-keypair.pem' ,databaseServer: databaseServer,
      zone:zone)
      if ( !masterServer.save() )
        masterServer.errors.each {
          log.debug it
        }
      zone.addToMobileServers(masterServer)
      zone.save()

      def mobileServer = new MobileServer(instanceId: 'i-1234',host: 'ec2-50-16-90-253.compute-1.amazonaws.com', loginUser: 'ubuntu',
              identity: '/Users/davec/Downloads/ec2keys/ec2-keypair.pem' ,databaseServer: databaseServer,
              replica: true,zone:zone)

      zone.addToMobileServers(mobileServer)
      zone.save()

      if (!mobileServer.save())
        mobileServer.errors.each {
          log.debug it
        }

      masterServer.addToReplicas(mobileServer)
      masterServer.save()

      databaseServer.addToOrganizations(organization)
      databaseServer.save()

      databaseServer = new DatabaseServer(instanceId: 'i-1235', host: 'ec2-50-16-90-249.compute-1.amazonaws.com', loginUser: 'ubuntu', identity: '/Users/davec/Downloads/ec2keys/ec2-keypair.pem',zone:zone )

      if (!databaseServer.save())
        databaseServer.errors.each {
          log.debug it
        }


      mobileServer = new MobileServer(instanceId: 'i-1234',host: 'ec2-50-16-90-255.compute-1.amazonaws.com', loginUser: 'ubuntu',
              identity: '/Users/davec/Downloads/ec2keys/ec2-keypair.pem' ,databaseServer: databaseServer,
              replica: true, zone:zone)

      if(!mobileServer.save())
        mobileServer.errors.each{
          log.debug it
        }

      masterServer.addToReplicas(mobileServer)
      masterServer.save()

      def org1 = new Organization(name: 'xtuple', connections: 10, active: true ).save()
      def org2 = new Organization(name: 'evive_live', connections: 3, active: true ).save()

    }
    def destroy = {
    }
}
