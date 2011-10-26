import org.gaiac.domain.*
import grails.util.GrailsUtil

class BootStrap {

    def init = { servletContext ->
      initRoles()
      initAccounts()
      fillGaiacFileSizes()
    }

    def destroy = {
    }

    private void fillGaiacFileSizes() {
      def emptySizeFiles = GaiacFile.findBySizeIsNull()
      emptySizeFiles.each {
        def pointsTo = it.concrete()
        if (pointsTo.isFile()) {
          it.size = pointsTo.size()
          it.save()
        }
      }
    }

    private void initRoles() {
      if (!Role.findByAuthority('ROLE_ADMIN')) {
        new Role(authority: 'ROLE_ADMIN').save()
      }
      
      if (!Role.findByAuthority('ROLE_BASIC')) {
        new Role(authority: 'ROLE_BASIC').save()
      }
    }

    private void initAccounts() {
      if(!Member.findByEmail('admin@gaiac.org')) {
        def admin = new Member( email: 'admin@gaiac.org', 
                                password: 'admin',
                                enabled: true,
                                accountLocked: false,
                                accountExpired: false,
                                passwordExpired: false).save(failOnError:true)          
        new MemberRole(member: admin, role: Role.findByAuthority('ROLE_ADMIN')).save()
      }
      
      switch (GrailsUtil.environment) {
        case "development":
          def member = new Member( email: 'member@gaiac.org',
                                    password: 'member',
                                    enabled: true,
                                    accountLocked: false,
                                    accountExpired: false,
                                    passwordExpired: false).save(failOnError:true)
          
          new MemberRole(member: member, role: Role.findByAuthority('ROLE_BASIC')).save()
          break
      }
    }
}
