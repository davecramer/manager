import com.xtuple.DatabaseServer
import com.xtuple.MobileServer
import com.xtuple.Organization

class BootStrap {

    def init = { servletContext ->

      def organization = new Organization(name: 'testorg',active: true)


      def databaseServer = new DatabaseServer(host: 'ec2-50-16-90-251.compute-1.amazonaws.com', sudoUser: 'ubuntu', identity: '/Users/davec/Downloads/ec2keys/ec2-keypair.pem' ).save()
      def masterServer = new MobileServer(host: 'ec2-50-16-90-252.compute-1.amazonaws.com', sudoUser: 'ubuntu', identity: '/Users/davec/Downloads/ec2keys/ec2-keypair.pem' ,databaseServer: databaseServer)

      databaseServer = new DatabaseServer(host: 'ec2-50-16-90-250.compute-1.amazonaws.com', sudoUser: 'ubuntu', identity: '/Users/davec/Downloads/ec2keys/ec2-keypair.pem' ).save()
      def mobileServer = new MobileServer(host: 'ec2-50-16-90-253.compute-1.amazonaws.com', sudoUser: 'ubuntu',
              identity: '/Users/davec/Downloads/ec2keys/ec2-keypair.pem' ,databaseServer: databaseServer,
              replica: true).save()

      masterServer.addToReplicas(mobileServer)
      databaseServer.addToOrganizations(organization)

      databaseServer = new DatabaseServer(host: 'ec2-50-16-90-249.compute-1.amazonaws.com', sudoUser: 'ubuntu', identity: '/Users/davec/Downloads/ec2keys/ec2-keypair.pem' ).save()
      mobileServer = new MobileServer(host: 'ec2-50-16-90-255.compute-1.amazonaws.com', sudoUser: 'ubuntu',
              identity: '/Users/davec/Downloads/ec2keys/ec2-keypair.pem' ,databaseServer: databaseServer,
              replica: true).save()
      masterServer.addToReplicas(mobileServer)
      masterServer.save()

      def org1 = new Organization(name: 'xtuple', active: true ).save()
      def org2 = new Organization(name: 'evive_live', active: true ).save()

    }
    def destroy = {
    }
}
