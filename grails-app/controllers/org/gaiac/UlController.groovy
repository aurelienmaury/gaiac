package org.gaiac

import grails.plugins.springsecurity.Secured
import grails.converters.JSON
import org.gaiac.domain.GaiacFile
import java.text.SimpleDateFormat

import static org.codehaus.groovy.grails.commons.ConfigurationHolder.config as conf

@Secured(['ROLE_ADMIN'])
class UlController {
    
    private static final String FORM_FILES_NAME = 'uploadList'

    def gaiacFileImportService

    def index() {
    }

    def upload() { 

      def destDir = getDestDir()

      request.getFiles(FORM_FILES_NAME).each { uploadedFile ->
        
        log.debug "Uploading: ${uploadedFile.originalFilename}"
        
        if (!gaiacFileImportService.matchesAnyAllowed(uploadedFile.originalFilename)) {
          log.error "Upload failed: ${uploadedFile.originalFilename} has no allowed extensions"
        } else {

          try {
            def onDiskFile = transferToDir(uploadedFile, destDir)
            def isSaved = createGaiacFileFor(onDiskFile)
              
            if (!isSaved) {
              log.error "Upload failed: ${onDiskFile.name} - deleting file"
              onDiskFile.delete()
            }

            log.debug "Upload succeed: ${onDiskFile.name}"
          } catch (Exception e) {
            log.error("Error uploading ${uploadedFile.originalFilename}", e)
          }
        }
      }

      return render(text: [success:true] as JSON, contentType:'text/json')
    }

    private createGaiacFileFor(onDiskFile) {
      def creationParams = [
        name: onDiskFile.name,
        path: onDiskFile.absolutePath, 
        size: onDiskFile.size()
      ]

      new GaiacFile(creationParams).save(flush: true)
    }

    private transferToDir(file, destDir) {
      def onDiskFile = new File(destDir.absolutePath + "/" + file.originalFilename)
      file.transferTo(onDiskFile)
      onDiskFile
    }

    private File getDestDir() {
      
      def repoPath = conf.gaiac.repository.path
      def today = Calendar.getInstance().getTime()
      def sdf = new SimpleDateFormat("yyyy-MM-dd")
      def destDir = new File(repoPath+"/"+sdf.format(today))
      if (!destDir.exists()) {
        destDir.mkdir()
      }
      destDir
    }
}
