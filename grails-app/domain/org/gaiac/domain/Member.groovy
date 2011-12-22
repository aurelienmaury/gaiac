package org.gaiac.domain

class Member {

  transient springSecurityService

  String email
  String password
  boolean enabled
  boolean accountExpired
  boolean accountLocked
  boolean passwordExpired

  Date dateCreated
  Date lastUpdated

  static constraints = {
    email(
        blank: false,
        unique: true,
        email: true,
        maxSize: 100)

    password(
        blank: false)
  }

  static mapping = {
    password(
        column: '`password`')
  }
  
  static Set<Member> getAllAdmins() {
    Role adminRole = Role.findByAuthority('ROLE_ADMIN')
    def allLinks = MemberRole.findAllByRole(adminRole)
    allLinks.collect {
      it.member
    }
  }

  Set<Role> getAuthorities() {
    MemberRole.findAllByMember(this).collect { it.role } as Set
  }

  def beforeInsert() {
    encodePassword()
  }

  def beforeUpdate() {
    if (isDirty('password')) {
      encodePassword()
    }
  }

  private void encodePassword() {
    password = springSecurityService.encodePassword(password)
  }
}
