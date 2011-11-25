<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'gaiacFile.label', default: 'GaiacFile')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>

<div id="edit-gaiacFile" class="content scaffold-edit" role="main">

    <div class="row">
        <div class="span16">

            <ul class="pills">
                <li><g:link action="list">
                    <g:message code="short.browse.label"/>
                </g:link>
                </li>
                <li class="active"><a href="#">
                    <g:message code="short.edit.label"/>
                </a>
                </li>
            </ul>

            <flash:all/>

            <g:hasErrors bean="${gaiacFileInstance}">
                <ul class="errors" role="alert">
                    <g:eachError bean="${gaiacFileInstance}" var="error">
                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                                error="${error}"/></li>
                    </g:eachError>
                </ul>
            </g:hasErrors>


            <g:form method="post">

                <g:hiddenField name="id"
                               value="${gaiacFileInstance?.id}"/>

                <g:hiddenField name="version"
                               value="${gaiacFileInstance?.version}"/>


                <fieldset class="form">
                    <g:render template="form"/>
                </fieldset>

                <div class="actions">
                    <g:actionSubmit action="update"
                                    value="${message(code: 'default.button.update.label', default: 'Update')}"
                                    class="btn primary"/>

                    <g:actionSubmit action="delete"
                                    value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                                    formnovalidate=""
                                    onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"
                                    class="btn"/>
                </div>
            </g:form>
        </div>
    </div>
</div>
</body>