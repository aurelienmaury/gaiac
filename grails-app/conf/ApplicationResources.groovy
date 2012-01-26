modules = {
  core {
    dependsOn 'jquery'
    resource url: '/css/bootstrap.css'
    resource url: '/css/main.css'
    resource url: '/js/bootstrap-alerts.js'
    resource url: '/js/bootstrap-dropdown.js'
  }

  uploader {
    resource url: '/css/jquery-ui-1.8.16.custom.css'
    resource url: '/js/jquery-ui-1.8.16.custom.min.js'
    resource url: '/js/jquery.ul.js'
  }
}