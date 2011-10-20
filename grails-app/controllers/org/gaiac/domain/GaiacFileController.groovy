package org.gaiac.domain

import java.text.SimpleDateFormat
import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(["hasAnyRole('ROLE_BASIC','ROLE_ADMIN')"])
class GaiacFileController {

  def grailsApplication
  
  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

  def afterInterceptor = { model ->
    model.activeTopbarSearch = true
  }

  def index() {
      redirect(action: "list", params: params)
  }

  def list() {
      params.max = Math.min(params.max ? params.int('max') : 10, 100)
      [gaiacFileInstanceList: GaiacFile.list(params), gaiacFileInstanceTotal: GaiacFile.count()]
  }

  def search() {
      def response = GaiacFile.search("*${params.query}*")
      log.debug "====> Result : $response"
      render view:'list', model: [  query: params.query, 
                                    gaiacFileInstanceList: response.results, 
                                    gaiacFileInstanceTotal: GaiacFile.count() ]
  }

  @Secured(['ROLE_ADMIN'])
  def create() {
      [gaiacFileInstance: new GaiacFile(params)]
  }

  @Secured(['ROLE_ADMIN'])
  def save() {
  
  def repoPath = grailsApplication.config.gaiac.repository.path
  
  def successfulUploads = []
  def errorUploads = []
  
  def today = Calendar.getInstance().getTime()
  def sdf = new SimpleDateFormat("yyyy-MM-dd")
  def destDir = new File(repoPath+"/"+sdf.format(today))
  if (!destDir.exists()) {
    destDir.mkdir()
  }
    
  request.getFiles('uploadList').each { uploadedFile ->
    def filePath = destDir.absolutePath + "/" + uploadedFile.originalFilename
    
    try {
    uploadedFile.transferTo(new File(filePath))
    def gaiacFileInstance = new GaiacFile(name:uploadedFile.originalFilename, path: filePath)
      
    if (gaiacFileInstance.save(flush: true)) {
      successfulUploads << gaiacFileInstance.name
    } else {
      errorUploads << gaiacFileInstance.name
    }
    } catch (Exception e) {
      log.error("Error uploading ${uploadedFile.originalFilename}", e)
      errorUploads << uploadedFile.originalFilename
    }
  }

  if (successfulUploads) {
    def listing = "<ul><li>"+successfulUploads.join('</li><li>')+"</li></ul>"
    flash.success = message(code: 'multi.upload.success', args: [listing])
  }
  
  if (errorUploads) {
    def listing = "<ul><li>"+errorUploads.join('</li><li>')+"</li></ul>"
    flash.error = message(code: 'multi.upload.error', args: [listing])
  }
      redirect(action: "list") 
  }

  def show() {
      def gaiacFileInstance = GaiacFile.get(params.id)
      if (!gaiacFileInstance) {
        flash.error = message(code: 'default.not.found.message', 
                              args: [ message(code: 'gaiacFile.label', default: 'GaiacFile'), params.id ])
        redirect(action: "list")
        return
      }

      [ gaiacFileInstance: gaiacFileInstance ]
  }



  @Secured(['ROLE_ADMIN'])
  def edit() {
      def gaiacFileInstance = GaiacFile.get(params.id)
      if (!gaiacFileInstance) {
          flash.error = message(code: 'default.not.found.message', args: [message(code: 'gaiacFile.label', default: 'GaiacFile'), params.id])
          redirect(action: "list")
          return
      }

      [gaiacFileInstance: gaiacFileInstance]
  }

  @Secured(['ROLE_ADMIN'])
  def update() {

    def gaiacFileInstance = GaiacFile.get(params.id)
    if (!gaiacFileInstance) {
      flash.error = message(code: 'default.not.found.message', args: [message(code: 'gaiacFile.label', default: 'GaiacFile'), params.id])
      redirect(action: "list")
      return
    }

    if (params.version) {
      def version = params.version.toLong()
      if (gaiacFileInstance.version > version) {
        gaiacFileInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                    [message(code: 'gaiacFile.label', default: 'GaiacFile')] as Object[],
                    "Another user has updated this GaiacFile while you were editing")
        render(view: "edit", model: [gaiacFileInstance: gaiacFileInstance])
        return
      }
    }

    gaiacFileInstance.properties = params

    if (!gaiacFileInstance.save(flush: true)) {  
      render(view: "edit", model: [gaiacFileInstance: gaiacFileInstance])
      return
    }

    flash.success = message(code: 'default.updated.message', args: [message(code: 'gaiacFile.label', default: 'GaiacFile'), gaiacFileInstance.id])
    redirect(action: "show", id: gaiacFileInstance.id)
  }

  @Secured(['ROLE_ADMIN'])
  def delete() {

    def gaiacFileInstance = GaiacFile.get(params.id)
    if (!gaiacFileInstance) {
      flash.error = message(code: 'default.not.found.message', args: [message(code: 'gaiacFile.label', default: 'GaiacFile'), params.id])
      redirect(action: "list")
      return
    }

    try {
      new File(gaiacFileInstance.path).delete()  
      gaiacFileInstance.delete(flush: true)
      flash.success = message(code: 'default.deleted.message', args: [message(code: 'gaiacFile.label', default: 'GaiacFile'), params.id])
      redirect(action: "list")
    }
    catch (DataIntegrityViolationException e) {
      flash.error = message(code: 'default.not.deleted.message', args: [message(code: 'gaiacFile.label', default: 'GaiacFile'), params.id])
      redirect(action: "show", id: params.id)
    }

  }
}
