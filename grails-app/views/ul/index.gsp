<html>

<head>
  <meta name="layout" content="main">
  <r:require module="uploader"/>
</head>

<body>
  <div class="content">
    <div class="row">
      <div class="span16">
      
        <h1>Upload area</h1>

        <form>
          <fieldset>
            <div class="clearfix">
              <label for="selector">Pick files</label>
              <div class="input">
                <input type="file"  id="selector" name="uploadList"  multiple="multiple" required=""/>

                <r:script>
                $(function() {
                  $('#selector').uploadify({submitId: 'submitUpload', url: '${createLink(action: 'upload')}'});
                });
                </r:script>

              </div>
            </div>
          </fieldset>

          <fieldset class="actions">
            <input type="button" id="submitUpload" class="btn primary" value="Upload"/>

            
          </fieldset>
        </form>
      </div>
    </div>
  </div>
</body>
</html>
