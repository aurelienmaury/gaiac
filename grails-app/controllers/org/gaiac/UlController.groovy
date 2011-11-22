package org.gaiac

import grails.plugins.springsecurity.Secured
import grails.converters.JSON
import org.gaiac.domain.GaiacFile
import java.text.SimpleDateFormat

@Secured(['ROLE_ADMIN'])
class UlController {

    def grailsApplication
    
    def index() {
    }

    def upload() { 

      def repoPath = grailsApplication.config.gaiac.repository.path
    
      def successfulUploads = []
      def errorUploads = []
      
      def today = Calendar.getInstance().getTime()
      def sdf = new SimpleDateFormat("yyyy-MM-dd")
      def destDir = new File(repoPath+"/"+sdf.format(today))
      if (!destDir.exists()) {
        destDir.mkdir()
      }

      println "UPLOAD REQUESTED : ${request}"
      request.getFiles("uploadList").each { uploadedFile ->
        println uploadedFile.originalFilename
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
      }

      return render(text: [success:true] as JSON, contentType:'text/json')
    }
}
