<html>
	<head>
		<meta name="layout" content="main">
	</head>
	<body>
		<div class="content">
			
			<div class="row">
				<div class="span16">
			
				<ul class="pills">
					<li>
						<g:link action="list"><g:message code="short.browse.label" /></g:link>
					</li>
					<li class="active">
						<a href="#"><g:message code="short.show.label" /></a>
					</li>
				</ul>
				
				<flash:all/>
				
				<form>
					<fieldset class="form">
					<div class="clearfix">
						<label for="name">
							<g:message code="gaiacFile.name.label" default="Name" />
						</label>
						<div class="input">
							<g:textField name="name" required="" value="${gaiacFileInstance?.name}" class="disabled" disabled="true"/>
						</div>
					</div>

					<div class="clearfix">
						<label for="categories">
							<g:message code="gaiacFile.categories.label" default="Categories" />
						</label>
						<div class="input">
							<span class="uneditable-input">
							<g:each in="${gaiacFileInstance?.categories}" var="cat">
								${fieldValue(bean: cat, field: 'name')}
							</g:each>
							</span>
						</div>
					</div>
				
					<sec:ifAllGranted roles="ROLE_ADMIN">
					<div class="clearfix">
						<label for="name">
							<g:message code="gaiacFile.path.label" default="Path" />
						</label>
						<div class="input">
							<g:textField name="path" required="" value="${gaiacFileInstance?.path}" class="disabled" disabled="true"/>
						</div>
					</div>
					</sec:ifAllGranted>
					</fieldset>
				</form>
				
				<g:form>
					<fieldset class="actions">
						<g:hiddenField name="id" value="${gaiacFileInstance?.id}" />
						<g:link class="btn primary" action="edit" id="${gaiacFileInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
						<g:actionSubmit class="btn" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</fieldset>
				</g:form>
				</div>
			</div>
		</div>
	</body>
</html>
