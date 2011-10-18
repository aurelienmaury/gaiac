<%@ page import="org.gaiac.domain.Member" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'member.label', default: 'Member')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		
		<div id="edit-member" class="content " role="main">
      <div class="page-header">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			</div>

			<div class="row">
			<div class="span16">
			
			<ul class="pills">
				<li><g:link class="list" action="list"><g:message code="short.list.label"/></g:link></li>
				<li><g:link class="create" action="create"><g:message code="short.new.label" /></g:link></li>
				<li class='active'><g:link class="edit" action="edit" id="${memberInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link></li>
				
			</ul>
			
			<flash:all/>
			
			<g:hasErrors bean="${memberInstance}">
			<div class="alert-message error">
				<ul>
				<li>TEST</li>
				<g:eachError bean="${memberInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
				</ul>
			</div>
			</g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${memberInstance?.id}" />
				<g:hiddenField name="version" value="${memberInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="actions">
					<g:actionSubmit class="btn primary" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
					<g:actionSubmit class="btn" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
			
			</div>
			</div>
		</div>
	</body>
</html>
