package org.gaiac

import org.gaiac.domain.GaiacFile
import org.gaiac.domain.Member
import grails.plugins.springsecurity.Secured

@Secured(["hasAnyRole('ROLE_BASIC','ROLE_ADMIN')"])
class DlController {

  def springSecurityService
  def grailsApplication

  def file = {
    

    def gaiacFile = GaiacFile.get(params.id)
    if (!gaiacFile) {
      flash.error = "The requested file can not be found."
      render view:"/managedError"
      return
    } 

    log.debug "Downloading file: ${gaiacFile.path}"
 
    try {
      Member.incDlNumber(currentUser.id)

      def file = new File(gaiacFile.path)
      if (file.exists()) {
          def filename = file.absolutePath.split('/').reverse()[0]
          response.setContentType("application/octet-stream")
          response.setHeader("Content-Disposition", "attachment; filename=${filename}")
          response.setContentLength(file.size() as int)
          
          int read = 0;
          byte[] bytes = new byte[1024];
 
          def output = response.outputStream
          file.withInputStream { input ->
            while ((read = input.read(bytes)) != -1) {
              output.write(bytes, 0, read);
            }
          }
          output.flush()
      }
    } finally {
      log.debug "End of download, can do something here"
    }
  }
}
