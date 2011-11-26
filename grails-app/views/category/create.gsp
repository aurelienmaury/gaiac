<head>
  <meta name="layout" content="main">
  <g:set var="entityName" value="${message(code: 'category.label', default: 'Category')}"/>
  <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<div class="content">

  <ul class="pills">
    <li><g:link action="list"><g:message code="short.list.label"/></g:link></li>
    <li class="active"><a href="#"><g:message code="short.new.label"/></a></li>
  </ul>

  <flash:all/>

  <g:hasErrors bean="${categoryInstance}">
    <div class="alert-message error">
    <g:eachError bean="${categoryInstance}" var="error">
      <li<g:if
        test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
        error="${error}"/></li>
    </g:eachError>
    </div>
  </g:hasErrors>
  <g:form action="save">
    <fieldset class="form">
      <g:render template="form"/>
    </fieldset>
    <fieldset class="actions">
      <g:submitButton name="create"
                      value="${message(code: 'default.button.create.label', default: 'Create')}"
                      class="btn primary"/>
    </fieldset>
  </g:form>
</div>
</body>