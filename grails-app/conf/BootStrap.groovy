import org.gaiac.domain.*


class BootStrap {

    def init = { servletContext ->
      def roleAdmin = new Role(authority: 'ROLE_ADMIN').save()
      def roleModo = new Role(authority: 'ROLE_MODO').save()
      def roleBasic = new Role(authority: 'ROLE_BASIC').save()

      def admin = new Member( email: 'admin@gaiac.org', 
                              password: 'admin',
                              enabled: true,
                              accountLocked: false,
                              accountExpired: false,
                              passwordExpired: false).save(failOnError:true)
							  
							  
							  
		new MemberRole(member: admin, role: roleAdmin).save()
		
		def member = new Member( email: 'member@gaiac.org',
			password: 'member',
			enabled: true,
			accountLocked: false,
			accountExpired: false,
			passwordExpired: false).save(failOnError:true)
			
		new MemberRole(member: member, role: roleBasic).save()
    }
    def destroy = {
    }
}
