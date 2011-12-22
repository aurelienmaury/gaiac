(function($) {

  var settings = {
    'url'  : '/upload',
    'submitId': 'sender'
  };

  var $this = null;

  var methods = {
    init : function(options) { // Initialises the plugin
      return $(this).each(function() {
        $this = $(this);

        if (options) {
          $.extend(settings, options);
        }

        methods.customiseDom();
        methods.bindEvents();
      });
    },

    customiseDom : function() {
      $listDiv = $('<div>').attr('id','ulList');
      $this.after($listDiv);
    },

    bindEvents : function() { // Bind plugin events
      $this.bind('change', methods.fileChange);
      $('#'+settings.submitId).click(methods.sendAll);
    },

    sendAll : function() {
      var files = $this.context.files;
      for (var i = 0, f; f = files[i]; i++) {
        var formData = new FormData();
        formData.append('uploadList', f); // Append each files to the form data
        
        methods.sendFormData(formData, i);
      }
    },

    fileChange : function(e) { // Fired when new files are selected manually or by drag+drop
      $('#ulList').html('');
      var files = e.originalEvent.target.files || e.originalEvent.dataTransfer.files;
      for (var i = 0, f; f = files[i]; i++) {

        var formData = new FormData();
        formData.append('uploadList', f); // Append each files to the form data

        $nameSpan = $('<span>').attr('id', 'ulFileName'+i).html(f.name);
        $progressSpan = $('<span>').attr('id', 'ulFileProgress'+i);
        $progressDiv = $('<div>').attr('id', 'progressbar'+i);


        $fileDiv = $('<div>').addClass('ulFile').append($nameSpan).append($progressSpan).append($progressDiv);

        $('#ulList').prepend($fileDiv);
      }

      ulCount = 0;
      
      return false;
    },

    sendFormData : function(formData, ulNum) {

      var xhr = new XMLHttpRequest();

      xhr.upload.addEventListener('progress', function(evt) {
        var percentComplete = Math.round(100 * evt.loaded / evt.total);
        $('#ulFileProgress'+ulNum).html('- '+percentComplete+' %');
        $('#progressbar'+ulNum).progressbar({value: percentComplete});

      }, false);

      xhr.open('POST', settings.url, true);
      xhr.onreadystatechange = methods.xhrStateChange;
      xhr.send(formData);
    },

    xhrStateChange : function() { // Used to call the callback when appropriate
      if (xhr.readyState === 4 && xhr.status === 200) {
        methods.uploaded(xhr.response);
      }
    },

    uploaded : function(resp) { // Default callback
      console.log(resp);
    }
  };

  $.fn.uploadify = function(method) {
    if (methods[method]) {
      return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
    }
    else if (typeof method === 'object' || !method) {
      return methods.init.apply(this, arguments);
    }
    else {
      $.error('Method ' +  method + ' does not exist on jQuery.uploadify');
    }
  };
})(jQuery);