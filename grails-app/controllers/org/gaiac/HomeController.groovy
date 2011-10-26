package org.gaiac

class HomeController {

    def index() { 
      //redirect(controller:'gaiacFile')
      render view:'dashboard'
    }
}
