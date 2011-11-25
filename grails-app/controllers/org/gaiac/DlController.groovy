package org.gaiac

import org.gaiac.domain.GaiacFile
import org.gaiac.domain.Member
import org.gaiac.domain.DownloadTrace
import grails.plugins.springsecurity.Secured

@Secured(["hasAnyRole('ROLE_BASIC','ROLE_ADMIN')"])
class DlController {

    def springSecurityService

    def dlKeeperService

    def file() {

        if (!params.id || !params.id.isLong()) {
            flash.error = "The requested file can not be found."
            render view: "/managedError"
            return
        }

        log.debug "File download request: id=${params.id}"

        def gaiacFile = GaiacFile.get(params.id)
        if (!gaiacFile) {
            log.debug "File with id: ${params.id} not found."
            flash.error = "The requested file can not be found."
            render view: "/managedError"
            return
        }

        try {
            def file = new File(gaiacFile.path)
            if (!file.exists()) {

                log.warn "File not found: ${gaiacFile.path} inspect file: id=${params.id}"

                flash.error = "The requested file can not be found."
                render view: "/managedError"
                return
            } else {

                if (!dlKeeperService.startDl(springSecurityService.principal.id)) {

                    log.warn "User ${springSecurityService.principal.username} has hit max download number while requesting file: id=${params.id}"

                    flash.error = "You've hit the max parallel download number."
                    render view: "/managedError"
                    return
                }


                def filename = file.absolutePath.split('/').reverse()[0]

                log.debug "Sending ${filename} to ${springSecurityService.principal.username} started"

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

                new DownloadTrace(file: gaiacFile, member: Member.get(springSecurityService.principal.id)).save()
                gaiacFile.downloadNumber++
                gaiacFile.save()
            }
        } finally {
            response.outputStream.flush()
            response.outputStream.close()

            dlKeeperService.finishDl(springSecurityService.principal.id)
            log.debug "Sending ${filename} to ${springSecurityService.principal.username} finished"
        }
    }
}
