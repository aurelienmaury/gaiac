<html>

<head>
  <meta name="layout" content="main">
</head>

<body>
<div class="content">

  <div class="row">
    <div class="span16">
      <g:form action="list" name="actionAdm">
        <fieldset class="form">
          <div class="clearfix">
            <label for="query" class="btnPreField">
              <g:actionSubmit class="btn primary"
                              style="display:inline"
                              action="search"
                              value="${message(code: 'default.button.search.label', default: 'Search')}"/>
            </label>

            <div class="input">
              <g:textField name="query" value="${query}" class="span11" id="searchField"/>
            </div>
          </div>

          <div class="clearfix">
            <g:each in="${categories}" var="cat">
              <g:checkBox name="selectedCat" value="${cat.id}" checked="${selectedCat?.contains(cat.id.toString())}"/> ${cat.name}
            </g:each>
          </div>
        </fieldset>
      </g:form>
    </div>
  </div>

  <div class="row">
    <div class="span16">

      <flash:all/>

      <g:form controller="gaiacFile">
        <table class="zebra-striped">
          <thead>
          <tr>
            <g:sortableColumn property="name"
                              title="${message(code: 'gaiacFile.name.label', default: 'Name')}"
                              params="[query: query]"/>

            <g:sortableColumn property="size"
                              title="${message(code: 'gaiacFile.size.label', default: 'Size')}"
                              params="[query: query]"
                              class="ctxt w2"/>

            <g:sortableColumn property="dateCreated"
                              title="${message(code: 'gaiacFile.downloadNumber.short.label', default: 'Uploaded')}"
                              params="[query: query]"
                              class="ctxt w1"/>

            <g:sortableColumn property="downloadNumber"
                              title="${message(code: 'gaiacFile.downloadNumber.short.label', default: 'Dl Nb')}"
                              params="[query: query]"
                              class="ctxt w1"/>

            <th class="w0"></th>
            <th class="w0"></th>
            <sec:ifAllGranted roles="ROLE_ADMIN">
              <th class="w2 ctxt">
                <g:actionSubmit value="DeleteAll"
                                class="btn secondary"
                                action="deleteAll"
                                onclick="return confirm('Are you sure???')"/>
              </th>
            </sec:ifAllGranted>
          </tr>
          </thead>
          <tbody>

          <g:each in="${gaiacFileInstanceList}" status="i" var="gaiacFileInstance">
            <tr>
              <td>
                <sec:ifAllGranted roles="ROLE_ADMIN">

                  <g:link action="edit"
                          id="${gaiacFileInstance.id}" title="${fieldValue(bean: gaiacFileInstance, field: "name")}">
                    <display:labelCut value="${fieldValue(bean: gaiacFileInstance, field: "name")}"
                                      max="60"/>
                  </g:link>

                </sec:ifAllGranted>

                <sec:ifAllGranted roles="ROLE_BASIC">

                  <g:link action="show"
                          id="${gaiacFileInstance.id}" title="${fieldValue(bean: gaiacFileInstance, field: "name")}">
                    <display:labelCut value="${fieldValue(bean: gaiacFileInstance, field: "name")}"
                                      max="60"/>
                  </g:link>

                </sec:ifAllGranted>
              </td>

              <td class="rtxt">
                <display:fileSize value="${gaiacFileInstance.size}"/>
              </td>

              <td class="ctxt">
                <g:formatDate format="dd/MM/yyyy" date="${gaiacFileInstance.dateCreated}"/>
              </td>



              <td class="ctxt">
                ${gaiacFileInstance.downloadNumber}
              </td>

              <td class="ctxt">
                <g:if test="${gaiacFileInstance.exturl}">
                  <g:link url="${gaiacFileInstance.exturl}">
                    <img src="${resource(dir: 'images', file: 'internet_16.png')}"/>
                  </g:link>
                </g:if>
              </td>

              <td class="ctxt">
                <g:link controller="dl" action="file" id="${gaiacFileInstance.id}">
                  <img src="${resource(dir: 'images', file: 'download_16.png')}"/>
                </g:link>
              </td>

              <sec:ifAllGranted roles="ROLE_ADMIN">
                <td class="ctxt">
                  <g:checkBox name="idList" value="${gaiacFileInstance.id}" checked="${false}"/>
                </td>
              </sec:ifAllGranted>
            </tr>
          </g:each>

          </tbody>
        </table>
      </g:form>

      <bs:paginate total="${gaiacFileInstanceTotal}" params="[query: query]"/>
    </div>
  </div>
</div>
</body>
</html>
