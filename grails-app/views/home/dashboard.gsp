<html>
<head>
  <meta name="layout" content="main">
</head>
<body>
  <div id="list-gaiacFile" class="content" role="main">
    

    <div class="row">
      <div class="span16">
          <flash:all/>
        <div class="hero-unit" style="text-align:center">
          <h1>Welcome</h1>
          <p>
          <g:form action="search" controller="gaiacFile">
            <fieldset class="form">
              <label for="query" class="btnPreField">
                <g:actionSubmit class="btn primary" style="display:inline" action="search" value="${message(code: 'default.button.search.label', default: 'Search')}" />
              </label>
              <div class="input">
                <g:textField name="query" value="${query}" class="span11" id="searchField"/>
              </div>    
            </div>
            </fieldset>
          </g:form>
          </p>
        </div>
      </div>
    </div>
        
    <div class="row">
      <div class="span5 offset1">
        <h2>Derniers ajouts</h2>
        <ol>
          <g:each var="file" in="${lastUploads}">
            <li><g:link action="file" controller="dl" id="${file.id}"><display:labelCut max="36" value="${fieldValue(bean: file, field: 'name')}"/></g:link></li>
          </g:each>
        </ol>
      </div>
      <div class="span5">
        <h2>Plébiscités (tout)</h2>
        <ol>
          <g:each var="file" in="${topDlAllTime}">
            <li><g:link action="file" controller="dl" id="${file.id}"><display:labelCut max="36" value="${fieldValue(bean: file, field: 'name')}"/></g:link></li>
          </g:each>
        </ol>
      </div>
      <div class="span5">
        <h2>Plébiscités (30 jours)</h2>
        <ol>
          <g:each var="file" in="${topDl30Days}">
            <li><g:link action="file" controller="dl" id="${file.id}"><display:labelCut max="36" value="${fieldValue(bean: file, field: 'name')}"/></g:link></li>
          </g:each>
        </ol>
      </div>
      
    </div>
    
    

        
        
    
  </div>
</body>
</html>
