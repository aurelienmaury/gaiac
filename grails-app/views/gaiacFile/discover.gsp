<html>

<head>
	<meta name="layout" content="main">
</head>

<body>
	<div class="content">
		
		<div class="row">
			<div class="span16">
			
				<flash:all/>
				
				<g:hasErrors bean="${gaiacFileInstance}">
					<ul class="errors" role="alert">
						<g:eachError bean="${gaiacFileInstance}" var="error">
							<li
								<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>>
								<g:message error="${error}" />
							</li>
						</g:eachError>
					</ul>
				</g:hasErrors>

				<ul class="pills">
					<li><g:link action="list">
							<g:message code="short.browse.label" />
						</g:link>
					</li>
					<sec:ifAllGranted roles="ROLE_ADMIN">
					<li><g:link action="create">
							<g:message code="short.upload.label" default="Upload"/>
						</g:link>
					</li>
					<li class="active"><a href="#"><g:message code="short.discover.label" default="Discover"/></a>
					</li>
					</sec:ifAllGranted>
					
				</ul>

				<g:form action="discover">
					<fieldset class="form">
						<div class="clearfix">
							<label for="pathToDiscover"><g:message code="discover.path.label" default="Server directory"/></label>
							<div class="input">
								<g:textField name="pathToDiscover"/>
							</div>
						</div>
					</fieldset>
					<fieldset class="actions">
						<g:submitButton name="create" class="btn primary" value="${message(code: 'short.discover.label', default:'Discover')}" />
					</fieldset>
				</g:form>
			</div>
		</div>
	</div>
</body>
</html>
