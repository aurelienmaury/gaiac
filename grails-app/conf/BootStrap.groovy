import org.gaiac.domain.*
import grails.util.GrailsUtil

class BootStrap {

  def init = { servletContext ->
    initRoles()
    initAccounts()
    initCategories()
  }

  def destroy = {
  }

  private void initCategories() {
    if (Category.count() == 0) {
      ['Application', 'Film', 'Série', 'Musique', 'Action', 'Comédie ', 'Comédie romantique', 'Cape et d’épée',
          'Drame', 'Aventure', 'SF', 'Fantastique', 'VF', 'VOST', 'Horreur', 'Frisson', 'Policier', 'Manga',
          'Rock', 'Blues', 'Punk', 'Jazz', 'Soul', 'Hard Rock', 'Metal'].each {
        if (!Category.findByName(it)) {
          new Category(name: it).save()
        }
      }
    }
  }

  private void initRoles() {
    if (!Role.findByAuthority('ROLE_ADMIN')) {
      new Role(authority: 'ROLE_ADMIN').save()
    }

    if (!Role.findByAuthority('ROLE_SUPERVISOR')) {
      new Role(authority: 'ROLE_SUPERVISOR').save()
    }

    if (!Role.findByAuthority('ROLE_BASIC')) {
      new Role(authority: 'ROLE_BASIC').save()
    }
  }

  private void initAccounts() {
    if (!Member.findByEmail('admin@gaiac.org')) {
      def admin = new Member(email: 'admin@gaiac.org',
          password: 'admin',
          enabled: true,
          accountLocked: false,
          accountExpired: false,
          passwordExpired: false).save(failOnError: true)
      new MemberRole(member: admin, role: Role.findByAuthority('ROLE_ADMIN')).save()
    }



    switch (GrailsUtil.environment) {
      case "development":
        def member = new Member(email: 'member@gaiac.org',
            password: 'member',
            enabled: true,
            accountLocked: false,
            accountExpired: false,
            passwordExpired: false).save(failOnError: true)

        new MemberRole(member: member, role: Role.findByAuthority('ROLE_BASIC')).save()

        def sup = new Member(email: 'sup@gaiac.org',
            password: 'sup',
            enabled: true,
            accountLocked: false,
            accountExpired: false,
            passwordExpired: false).save(failOnError: true)

        new MemberRole(member: sup, role: Role.findByAuthority('ROLE_SUPERVISOR')).save()
        break
    }
  }
}
