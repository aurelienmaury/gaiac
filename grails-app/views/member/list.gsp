
<%@ page import="org.gaiac.domain.Member" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'member.label', default: 'Member')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="list-member" class="content scaffold-list" role="main">
			<div class="page-header">
			<h1><g:message code='members.label' default='Members' /></h1>
			</div>
			<div class="row">
			<div class="span16">
			<ul class="pills">
				<li class="active"><a href="#"><g:message code="short.list.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="short.new.label" /></g:link></li>
			</ul>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="email" title="${message(code: 'member.email.label', default: 'Email')}" />
					
						<g:sortableColumn property="accountExpired" title="${message(code: 'member.accountExpired.label', default: 'Account Expired')}" />
					
						<g:sortableColumn property="accountLocked" title="${message(code: 'member.accountLocked.label', default: 'Account Locked')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'member.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="enabled" title="${message(code: 'member.enabled.label', default: 'Enabled')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${memberInstanceList}" status="i" var="memberInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${memberInstance.id}">${fieldValue(bean: memberInstance, field: "email")}</g:link></td>
					
						<td><g:formatBoolean boolean="${memberInstance.accountExpired}" /></td>
					
						<td><g:formatBoolean boolean="${memberInstance.accountLocked}" /></td>
					
						<td><g:formatDate date="${memberInstance.dateCreated}" /></td>
					
						<td><g:formatBoolean boolean="${memberInstance.enabled}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${memberInstanceTotal}" />
			</div>
			</div>
			</div>
		</div>
	</body>
</html>
