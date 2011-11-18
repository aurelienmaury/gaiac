package org.gaiac.domain

import java.text.SimpleDateFormat
import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(["hasAnyRole('ROLE_BASIC','ROLE_ADMIN')"])
class GaiacFileController {

  def grailsApplication

  def gaiacFileImportService
  
  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

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

      if (gaiacFileImportService.matchesAnyAllowed(uploadedFile.originalFilename)) {
        def filePath = destDir.absolutePath + "/" + uploadedFile.originalFilename
        
        try {
          def onDiskFile = new File(filePath)
          uploadedFile.transferTo(onDiskFile)
          def gaiacFileInstance = new GaiacFile(name:uploadedFile.originalFilename, path: filePath, size: onDiskFile.size())
            
          if (gaiacFileInstance.save(flush: true)) {
            successfulUploads << gaiacFileInstance.name
          } else {
            errorUploads << gaiacFileInstance.name
          }
        } catch (Exception e) {
          log.error("Error uploading ${uploadedFile.originalFilename}", e)
          errorUploads << uploadedFile.originalFilename
        }
      } else {
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

      gaiacFileImportService.findAllFileToRegister(startingPoint).each { current ->
        def register = new GaiacFile()
        register.name = current.name
        register.path = current.absolutePath
        register.size = current.size()
        register.save()
      }
      
      flash.success = "Successfully discovered."
      redirect action:'list'
      return
    }
  }

}
