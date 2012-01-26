package org.gaiac

import grails.plugins.springsecurity.Secured
import grails.converters.JSON
import org.gaiac.domain.GaiacFile
import java.text.SimpleDateFormat
import org.gaiac.domain.Member
import org.gaiac.domain.MemberRole
import org.gaiac.domain.Role

@Secured(['ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_BASIC'])
class UlController {

  private static final String FORM_FILES_NAME = 'uploadList'

  def grailsApplication

  def gaiacFileImportService

  def mailService

  static final def dateFormatter = new SimpleDateFormat("yyyy-MM-dd")

  def index() {
  }

  def upload() {

    def destDir = getDestDir()

    request.getFiles(FORM_FILES_NAME).each { uploadedFile ->

      log.debug "Uploading: ${uploadedFile.originalFilename}"

      if (!gaiacFileImportService.isImportAllowed(uploadedFile.originalFilename)) {
        log.error "Upload failed: ${uploadedFile.originalFilename} has no allowed extensions"
      }

      try {
        def onDiskFile = transferToDir(uploadedFile, destDir)

        if (!createGaiacFileFor(onDiskFile)) {
          log.error "Upload failed: ${onDiskFile.name} - deleting file"
          onDiskFile.delete()
        }

        log.debug "Upload succeed: ${onDiskFile.name}"


        Member.getAllAdmins().each { adminMember ->
          mailService.sendMail {
            to adminMember.email
            from "noreply@gaiac.org"
            subject "[GAIAC] New file uploaded : ${onDiskFile.name}"
            body "A new file has been uploaded : ${onDiskFile.name} on your Gaïac instance."
          }
        }
      } catch (Exception e) {
        log.error("Error uploading ${uploadedFile.originalFilename}", e)
      }
    }

    return render(text: [success: true] as JSON, contentType: 'text/json')
  }

  /**
   *
   * @param onDiskFile
   * @return
   */
  private createGaiacFileFor(onDiskFile) {
    def creationParams = [
        name: onDiskFile.name,
        path: onDiskFile.absolutePath,
        size: onDiskFile.size()
    ]

    new GaiacFile(creationParams).save(flush: true)
  }

  /**
   *
   * @param file
   * @param destDir
   * @return
   */
  private transferToDir(file, destDir) {
    def onDiskFile = new File(destDir.absolutePath + File.separatorChar + file.originalFilename)
    file.transferTo(onDiskFile)
    onDiskFile
  }

  /**
   *
   * @return
   */
  private File getDestDir() {
    def repoPath = grailsApplication.config.gaiac.repository.path

    def destDir = new File(repoPath + File.separatorChar + dateFormatter.format(Calendar.getInstance().getTime()))
    if (!destDir.exists()) {
      destDir.mkdir()
    }
    destDir
  }
}
