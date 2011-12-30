package org.gaiac.domain

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(["hasAnyRole('ROLE_BASIC','ROLE_ADMIN')"])
class GaiacFileController {

  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

  def gaiacFileImportService

  def searchableService

  def index() {
    log.debug "test"
    redirect(action: "list", params: params)
  }


  def list() {
    params.max = Math.min(params.max ? params.int('max') : 10, 100)

    def res = GaiacFile.list(params)

    [gaiacFileInstanceList: res, gaiacFileInstanceTotal: GaiacFile.count(), categories: Category.list(order: 'name')]
  }


  def search() {
    if (!(params.query || params.selectedCat)) {
      return redirect(action: 'list')
    }

    params.max = Math.min(params.max ? params.int('max') : 10, 100)

    String forgedQuery = params.query.split(' ').collect {"(*${it}* OR *${it} OR ${it}*)"}.join(" AND ")
    def indexResponse = GaiacFile.search(forgedQuery)
    def fileList = GaiacFile.findAllByIdInList(indexResponse.results*.id, params)

    def selectedCat = [] << params.selectedCat
    selectedCat = selectedCat.flatten()

    if (params.selectedCat) {
      def matchingCat


      log.debug "selected cat = ${selectedCat}"
      def fileByCat = GaiacFile.where {
        categories.id in selectedCat.collect {it as Long}
      }.list()

      fileList = fileList.intersect(fileByCat)
    }



    render(view: 'list',
        model: [query: params.query,
            gaiacFileInstanceList: fileList,
            gaiacFileInstanceTotal: indexResponse.total,
            categories: Category.list(order: 'name'),
            selectedCat: selectedCat]
    )
  }

  def show() {
    def gaiacFileInstance = GaiacFile.get(params.id)
    if (!gaiacFileInstance) {
      flash.error = message(code: 'default.not.found.message',
          args: [msgGaiacFileLabel(), params.id])
      redirect(action: "list")
      return
    }

    [gaiacFileInstance: gaiacFileInstance]
  }


  @Secured(['ROLE_ADMIN'])
  def edit() {
    def gaiacFileInstance = GaiacFile.get(params.id)
    if (!gaiacFileInstance) {
      flash.error = message(code: 'default.not.found.message', args: [msgGaiacFileLabel(), params.id])
      redirect(action: "list")
      return
    }

    [gaiacFileInstance: gaiacFileInstance, categories: Category.list(order: 'name')]
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
    //gaiacFileInstance.categories = Category.getAll(params.categories)

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

  @Secured(['ROLE_ADMIN'])
  def deleteAll() {

    log.debug "idList = ${params.idList}"
    def allId = [] << params.idList
    allId = allId.flatten()
    def gaiacFileInstanceList = GaiacFile.getAll(allId)
    if (!gaiacFileInstanceList) {
      flash.error = message(code: 'default.not.found.message', args: [msgGaiacFileLabel(), params.idList])
      redirect(action: "list")
      return
    }

    try {
      gaiacFileInstanceList.each { gaiacFileInstance ->
        new File(gaiacFileInstance.path).delete()
        gaiacFileInstance.delete(flush: true)
      }
      flash.success = message(code: 'default.deleted.message', args: [msgGaiacFileLabel(), allId])
      redirect(action: "list")
    }
    catch (DataIntegrityViolationException e) {
      flash.error = message(code: 'default.not.deleted.message', args: [msgGaiacFileLabel(), params.id])
      redirect(action: "show", id: params.id)
    }
  }

  @Secured(['ROLE_ADMIN'])
  def discover() {
    if (params.pathToDiscover) {
      def startingPoint = new File(params.pathToDiscover)

      if (!startingPoint.exists()) {
        flash.error = "Path not found."
        return
      }

      if (!startingPoint.isDirectory()) {
        flash.error = 'Path is not a directory.'
        return
      }

      gaiacFileImportService.importFrom(startingPoint)

      flash.success = "Successfully discovered."

      redirect action: 'list'
      return
    }
  }

  private def msgGaiacFileLabel() {
    message(code: 'gaiacFile.label', default: 'GaiacFile')
  }
}
