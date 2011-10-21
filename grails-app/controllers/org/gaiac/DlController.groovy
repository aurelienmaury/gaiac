package gaiac

import org.gaiac.domain.GaiacFile

class DlController {

    def file = {
      def gaiacFile = GaiacFile.get(params.id)
      if (!gaiacFile) {
        flash.error = "The requested file can not be found."
        render view:"/managedError"
        return
      } else {
        log.debug "Downloading file: ${gaiacFile.path}"
   
        def file = new File(gaiacFile.path)
        if (file.exists()) {
            def filename = file.absolutePath.split('/').reverse()[0]
           response.setContentType("application/octet-stream")
           response.setHeader("Content-Disposition", "attachment; filename=${filename}")
           response.setContentLength(file.readBytes().size())
           response.getOutputStream() << file.readBytes()
        }
        response.flush()  
        flash.success = "${filename} successfully downloaded."
        redirect controller:'gaiacFile'
      }
    }
}
