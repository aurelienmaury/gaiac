
<%@ page import="org.gaiac.domain.GaiacFile"%>
<!doctype html>
<html>
<head>
	<meta name="layout" content="main">
</head>
<body>
	<div id="list-gaiacFile" class="content" role="main">
		<div class="page-header">
			<h1><g:message code='gaiacFile.label' default='GaiacFile' /></h1>
		</div>
		
		<div class="row">
			<div class="span16">

				<ul class="pills">
					<li class="active"><g:link action="list">
							<g:message code="short.list.label" />
						</g:link>
					</li>
					<sec:ifAllGranted roles="ROLE_ADMIN">
					<li><g:link action="create">
							<g:message code="short.upload.label" default="Upload"/>
						</g:link>
					</li>
					</sec:ifAllGranted>
				</ul>
			</div>
		</div>
				
		<div class="row">
			<div class="span16">
				<g:form action="search">
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
			</div>
		</div>
		<div class="row">
			<div class="span16">

				<flash:all/>

				<table class="zebra-striped">
					<thead>
						<tr>

							<g:sortableColumn property="name"
								title="${message(code: 'gaiacFile.name.label', default: 'Name')}" />

						</tr>
					</thead>
					<tbody>
						<g:each in="${gaiacFileInstanceList}" status="i"
							var="gaiacFileInstance">
							<tr>
								<td><g:link action="show" id="${gaiacFileInstance.id}">
										${fieldValue(bean: gaiacFileInstance, field: "name")}
									</g:link>
								</td>
							</tr>
						</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<g:paginate total="${gaiacFileInstanceTotal}" />
				</div>
			</div>
		</div>
	</div>
</body>
</html>
