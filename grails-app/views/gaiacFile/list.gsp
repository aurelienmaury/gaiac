<html>

<head>
    <meta name="layout" content="main">
</head>

<body>
<div class="content">

    <div class="row">
        <div class="span16">
            <g:form action="list">
                <fieldset class="form">
                    <label for="query" class="btnPreField">
                        <g:actionSubmit class="btn primary"
                                        style="display:inline"
                                        action="search"
                                        value="${message(code: 'default.button.search.label', default: 'Search')}"/>
                    </label>

                    <div class="input">
                        <g:textField name="query" value="${query}" class="span11" id="searchField"/>
                    </div>
                </fieldset>
            </g:form>
        </div>
    </div>

    <div class="row">
        <div class="span16">

            <flash:all/>

            <table class="zebra-striped">
                <thead>
                <tr>
                    <g:sortableColumn property="name"
                                      title="${message(code: 'gaiacFile.name.label', default: 'Name')}"
                                      params="[query: query]"/>

                    <g:sortableColumn property="size"
                                      title="${message(code: 'gaiacFile.size.label', default: 'Size')}"
                                      params="[query: query]"
                                      class="ctxt w2" />

                    <g:sortableColumn property="downloadNumber"
                                      title="${message(code: 'gaiacFile.downloadNumber.short.label', default: 'Dl Nb')}"
                                      params="[query: query]"
                                      class="ctxt w2"/>

                    <th class="w2"></th>
                </tr>
                </thead>
                <tbody>

                <g:each in="${gaiacFileInstanceList}" status="i" var="gaiacFileInstance">
                    <tr>
                        <td>
                            <sec:ifAllGranted roles="ROLE_ADMIN">

                                <g:link action="show"
                                        id="${gaiacFileInstance.id}">
                                    <display:labelCut value="${fieldValue(bean: gaiacFileInstance, field: "name")}"
                                                      max="80"/>
                                </g:link>

                            </sec:ifAllGranted>

                            <sec:ifAllGranted roles="ROLE_BASIC">

                                <display:labelCut value="${fieldValue(bean: gaiacFileInstance, field: "name")}"
                                                  max="80"/>

                            </sec:ifAllGranted>
                        </td>

                        <td class="rtxt">
                            <display:fileSize value="${gaiacFileInstance.size}"/>
                        </td>

                        <td class="ctxt">
                            ${gaiacFileInstance.downloadNumber}
                        </td>

                        <td class="ctxt">
                            <g:link controller="dl" action="file" id="${gaiacFileInstance.id}">
                                <img src="${resource(dir: 'images', file: 'download_16.png')}"/>
                            </g:link>
                        </td>
                    </tr>
                </g:each>

                </tbody>
            </table>

            <bs:paginate total="${gaiacFileInstanceTotal}" params="[query: query]"/>
        </div>
    </div>
</div>
</body>
</html>
