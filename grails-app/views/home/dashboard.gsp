<html>
<head>
  <meta name="layout" content="main">
</head>

<body>
<div class="row">
  <flash:all/>

<div class="hero-unit" style="text-align:center">
  <h1>Welcome</h1>
<p>
  <g:form action="search" controller="gaiacFile">
    <fieldset class="form">
  <label for="query" class="btnPreField">
    <g:actionSubmit class="btn primary" style="display:inline" action="search"
                    value="${message(code: 'default.button.search.label', default: 'Search')}"/>
    </label>
    <div class="input">
      <g:textField name="query" value="${query}" class="span11" id="searchField"/>
    </div>
    </div>
    </fieldset>
  </g:form>
</p>
</div>


<div class="row">
  <div class="span-one-third">
    <div class="ctxt"><h2><g:link controller="gaiacFile" action="list" params="[sort:'dateCreated', max: 10, order: 'desc']">Derniers ajouts</g:link></h2></div>
    <ol>
      <g:each var="file" in="${lastUploads}">
        <li>
          <g:link action="file" controller="dl" id="${file.id}" title="${fieldValue(bean: file, field: 'name')}">
            <display:labelCut max="36" value="${fieldValue(bean: file, field: 'name')}"/>
          </g:link>
        </li>
      </g:each>
    </ol>
  </div>

  <div class="span-one-third">
    <div class="ctxt"><h2>Top 5 (30j)</h2></div>
    <ol>
      <g:each var="file" in="${topDl30Days}">
        <li>
          <g:link action="file" controller="dl" id="${file.id}" title="${fieldValue(bean: file, field: 'name')}">
            <display:labelCut max="36" value="${fieldValue(bean: file, field: 'name')}"/>
          </g:link>
        </li>
      </g:each>
    </ol>
  </div>

  <div class="span-one-third">
    <div class="ctxt"><h2>Top 5</h2></div>
    <ol>
      <g:each var="file" in="${topDlAllTime}">
        <li>
          <g:link action="file" controller="dl" id="${file.id}" title="${fieldValue(bean: file, field: 'name')}">
            <display:labelCut max="36" value="${fieldValue(bean: file, field: 'name')}"/>
          </g:link>
        </li>
      </g:each>
    </ol>
  </div>

</div>

</body>
</html>
