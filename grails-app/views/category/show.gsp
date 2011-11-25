
<%@ page import="org.gaiac.domain.Category" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'category.label', default: 'Category')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="content">
			
      <div class="row">
      <div class="span16">

		  <ul class="pills">
				<li><g:link action="list"><g:message code="short.list.label"/></g:link></li>
				<li><g:link action="create"><g:message code="short.new.label"/></g:link></li>
				<li class="active"><a href="#"><g:message code="short.show.label"/></a></li>
      </ul>

      <flash:all/>
      
<form>
			<fieldset class="form">

			<div class="clearfix ${hasErrors(bean: categoryInstance, field: 'name', 'error')} required">
  <label for="name">
		<g:message code="category.name.label" default="Name" />
    <span class="required-indicator">*</span>
  </label>
  <div class="input">
    <g:textField name="email" required="" value="${categoryInstance?.name}"/>
  </div>
</div>

<div class="clearfix ${hasErrors(bean: categoryInstance, field: 'name', 'description')}">
	<label for="description">
		<g:message code="category.description.label" default="Description" />
	</label>
	<div class="input">
		<g:textArea name="description" cols="40" rows="5" maxlength="1024" value="${categoryInstance?.description}"/>
		</div>
</div>


</fieldset>
		</form>

			<g:form>
				<fieldset class="actions">
					<g:hiddenField name="id" value="${categoryInstance?.id}" />
					<g:link class="btn primary" action="edit" id="${categoryInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="btn" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>

		</div>
	</body>
</html>
