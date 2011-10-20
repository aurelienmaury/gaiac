package org.gaiac.domain

import org.springframework.dao.DataIntegrityViolationException

class MemberController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def afterInterceptor = { model ->
      model.activeTopbarMembers = true
    }

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [memberInstanceList: Member.list(params), memberInstanceTotal: Member.count()]
    }

    def create() {
        def rolesList = Role.list()
        def checkRoles = rolesList.collectEntries { index -> [ (index.id): false ] }
        [memberInstance: new Member(params), rolesList: rolesList, checkRoles: checkRoles]
    }

    def save() {

        def memberInstance = new Member(params)

        if (params.confirmPassword != params.password) {
            memberInstance.errors.rejectValue('password','confirmation.false')
            render(view: 'create', model: [memberInstance: memberInstance])
            return
        }

        if (!memberInstance.save(flush: true)) {
            render(view: 'create', model: [memberInstance: memberInstance])
            return
        }

        flash.success = message(code: 'default.created.message', args: [message(code: 'member.label', default: 'Member'), memberInstance.id])
        redirect(action: "show", id: memberInstance.id)
    }

    def show() {
        def memberInstance = Member.get(params.id)
        if (!memberInstance) {
      flash.error = message(code: 'default.not.found.message', args: [message(code: 'member.label', default: 'Member'), params.id])
            redirect(action: "list")
            return
        }

        [memberInstance: memberInstance]
    }


    def edit() {
      def memberInstance = Member.get(params.id)

      if (!memberInstance) {
        flash.error = message(code: 'default.not.found.message', args: [message(code: 'member.label', default: 'Member'), params.id])
        redirect(action: "list")
        return
      }

      def rolesList = Role.list()
      
      def existingRoles = memberInstance.getAuthorities()

      def checkRoles = rolesList.collectEntries { index -> [(index.id): existingRoles.find { role -> role.id == index.id } ] }

      [memberInstance: memberInstance, rolesList: rolesList, checkRoles: checkRoles]
    }

    def update() {
      def memberInstance = Member.get(params.id)
      if (!memberInstance) {
          flash.error = message(code: 'default.not.found.message', args: [message(code: 'member.label', default: 'Member'), params.id])
          redirect(action: "list")
          return
      }

      if (params.version) {
          def version = params.version.toLong()
          if (memberInstance.version > version) {
              memberInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'member.label', default: 'Member')] as Object[],
                        "Another user has updated this Member while you were editing")
              render(view: "edit", model: [memberInstance: memberInstance])
              return
          }
      }

      if (params.confirmPassword != params.password) {
          memberInstance.errors.rejectValue('password','confirmation.false')
          render(view: "edit", model: [memberInstance: memberInstance])
          return
      }

      if (!params.roles) {
        flash.error = message(code: 'member.role.required', default: "Role is required")
        def rolesList = Role.list()
        def checkRoles = rolesList.collectEntries { index -> [ (index.id): false ] }
        render(view: "edit", model: [memberInstance: memberInstance, roleError: true, rolesList: rolesList, checkRoles: checkRoles])
        return
      }

      memberInstance.properties = params


      if (!memberInstance.save()) {
          render(view: "edit", model: [memberInstance: memberInstance])
          return
      } else {
          flash.success = message(    code: 'default.updated.message', 
                                      args: [ message(code: 'member.label', default: 'Member'), memberInstance.email ] )
      }

      def currentRoles = memberInstance.authorities
      def selectedRoles = Role.getAll(params.roles.collect { new Long(it) })

      def rolesToAdd = selectedRoles.findAll { ! (it.id in currentRoles*.id) }
      def rolesToDel = currentRoles.findAll { ! (it.id in selectedRoles*.id) }
      
      rolesToAdd.each { MemberRole.create(memberInstance, it) }      
      rolesToDel.each { MemberRole.remove(memberInstance, it) }

      redirect(action: "show", id: memberInstance.id)
    }

    def delete() {
        def memberInstance = Member.get(params.id)
        if (!memberInstance) {
          flash.error = message(code: 'default.not.found.message', args: [message(code: 'member.label', default: 'Member'), params.id])
          redirect(action: "list")
          return
        }

        try {
          memberInstance.delete(flush: true)
          flash.success = message(code: 'default.deleted.message', args: [message(code: 'member.label', default: 'Member'), params.id])
          redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
          flash.error = message(code: 'default.not.deleted.message', args: [message(code: 'member.label', default: 'Member'), params.id])
          redirect(action: "show", id: params.id)
        }
    }
}
