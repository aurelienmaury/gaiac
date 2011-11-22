package org.gaiac.domain

import java.text.SimpleDateFormat
import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(["hasAnyRole('ROLE_BASIC','ROLE_ADMIN')"])
class GaiacFileController {

  static allowedMethods = [save: "POST", update: "POST", delete: "POST", search: "POST"]

  def gaiacFileImportService
  

  def index() {
      redirect(action: "list", params: params)
  }


  def list() {
      params.max = Math.min(params.max ? params.int('max') : 10, 100)
      [gaiacFileInstanceList: GaiacFile.list(params), gaiacFileInstanceTotal: GaiacFile.count()]
  }


  def search() {

    if (!params.query) {
      redirect action:'list'
      return
    }
      
    params.max = Math.min(params.max ? params.int('max') : 10, 100)
    def sort = params.sort
    params.sort = null

    def response = GaiacFile.search("*${params.query}* OR ${params.query}", params)

    def fileList = []
    if (response.results) {
      if (!sort) {
        fileList = GaiacFile.getAll(response.results*.id)
      } else {
        params.sort = sort
        fileList = GaiacFile.findAllByIdInList(response.results*.id, params)
      }
    }
    
    render view:'list', model: [  query: params.query, 
                                  gaiacFileInstanceList: fileList, 
                                  gaiacFileInstanceTotal: response.total ]
  }


  def show() {
      def gaiacFileInstance = GaiacFile.get(params.id)
      if (!gaiacFileInstance) {
        flash.error = message(code: 'default.not.found.message', 
                              args: [ msgGaiacFileLabel(), params.id ])
        redirect(action: "list")
        return
      }

      [ gaiacFileInstance: gaiacFileInstance ]
  }


  @Secured(['ROLE_ADMIN'])
  def edit() {
      def gaiacFileInstance = GaiacFile.get(params.id)
      if (!gaiacFileInstance) {
          flash.error = message(code: 'default.not.found.message', args: [msgGaiacFileLabel(), params.id])
          redirect(action: "list")
          return
      }

      [gaiacFileInstance: gaiacFileInstance]
  }


  @Secured(['ROLE_ADMIN'])
  def update() {

    def gaiacFileInstance = GaiacFile.get(params.id)

    if (!gaiacFileInstance) {
      flash.error = message(code: 'default.not.found.message', args: [msgGaiacFileLabel(), params.id])
      return redirect(action: "list")
    }

    if (params.version) {
      def version = params.version.toLong()
      if (gaiacFileInstance.version > version) {
        gaiacFileInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                    [msgGaiacFileLabel()] as Object[],
                    "Another user has updated this GaiacFile while you were editing")
        return render(view: "edit", model: [gaiacFileInstance: gaiacFileInstance])
      }
    }

    gaiacFileInstance.properties = params

    if (!gaiacFileInstance.save(flush: true)) {  
      return render(view: "edit", model: [gaiacFileInstance: gaiacFileInstance])
    }

    flash.success = message(code: 'default.updated.message', args: [msgGaiacFileLabel(), gaiacFileInstance.id])
    redirect(action: "show", id: gaiacFileInstance.id)
  }


  @Secured(['ROLE_ADMIN'])
  def delete() {

    def gaiacFileInstance = GaiacFile.get(params.id)
    if (!gaiacFileInstance) {
      flash.error = message(code: 'default.not.found.message', args: [msgGaiacFileLabel(), params.id])
      redirect(action: "list")
      return
    }

    try {
      new File(gaiacFileInstance.path).delete()  
      gaiacFileInstance.delete(flush: true)
      flash.success = message(code: 'default.deleted.message', args: [msgGaiacFileLabel(), params.id])
      redirect(action: "list")
    }
    catch (DataIntegrityViolationException e) {
      flash.error = message(code: 'default.not.deleted.message', args: [msgGaiacFileLabel(), params.id])
      redirect(action: "show", id: params.id)
    }
  }


  private def msgGaiacFileLabel() {
    message(code: 'gaiacFile.label', default: 'GaiacFile')
  }


  @Secured(['ROLE_ADMIN'])
  def discover = {
    if (params.pathToDiscover) {
      def startingPoint = new File(params.pathToDiscover)
      
      if (!startingPoint.exists()) {
        flash.error = "Path not found."
        return
      }

      if (!startingPoint.isDirectory()) {
        flash.error = "Path is not a directory."
        return
      }

      gaiacFileImportService.importFrom(startingPoint)
      
      flash.success = "Successfully discovered."

      redirect action:'list'
      return
    }
  }
}
