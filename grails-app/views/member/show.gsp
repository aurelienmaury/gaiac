
<%@ page import="org.gaiac.domain.Member" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'member.label', default: 'Member')}" />
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
				
				<div class="clearfix">
					<label for="email">
						<g:message code="gaiacFile.email.label" default="Email" />
					</label>
					<div class="input">
						<g:textField name="email" value="${memberInstance?.email}" class="disabled" disabled="true"/>
					</div>
				</div>
			
<div class="clearfix">
	<label for="accountExpired">
		<g:message code="member.accountExpired.label" default="Account Expired" />
	</label>
	<div class="input">
	<g:checkBox name="accountExpired" value="${memberInstance?.accountExpired}" disabled="true" class="disabled"/>
	</div>
</div>

<div class="clearfix">
	<label for="accountLocked">
		<g:message code="member.accountLocked.label" default="Account Locked" />
		
	</label>
	<div class="input">
	<g:checkBox name="accountLocked" value="${memberInstance?.accountLocked}" disabled="true" class="disabled"/>
	</div>
</div>

<div class="clearfix ${hasErrors(bean: memberInstance, field: 'enabled', 'error')} ">
	<label for="enabled">
		<g:message code="member.enabled.label" default="Enabled" />
		
	</label>
	<div class="input">
	<g:checkBox name="enabled" value="${memberInstance?.enabled}" disabled="true" class="disabled"/>
	</div>
</div>

<div class="clearfix">
	<label for="passwordExpired">
		<g:message code="member.passwordExpired.label" default="Password Expired" />
	</label>
	<div class="input">
	<g:checkBox name="passwordExpired" value="${memberInstance?.passwordExpired}" disabled="true" class="disabled"/>
	</div>
</div>

			</fieldset>
		</form>
  		
		
			<g:form>
				<fieldset class="actions">
					<g:hiddenField name="id" value="${memberInstance?.id}" />
					<g:link class="btn primary" action="edit" id="${memberInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
		</div>
		</div>
	</body>
</html>
