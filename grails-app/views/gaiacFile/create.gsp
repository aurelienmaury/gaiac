<html>

<head>
	<meta name="layout" content="main">
</head>

<body>
	<div class="content">
		
		<div class="row">
			<div class="span16">
			
				<h1>Upload file</h1>

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
				
				<g:form action="create" enctype="multipart/form-data">
					
					<fieldset class="form">
						<div class="clearfix">
							<label for="actualFile"><g:message code="short.upload.label"/> </label>
							<div class="input">
								<input type="file" name="uploadList"  multiple="multiple" required=""/>
							</div>
						</div>
					</fieldset>

					<fieldset class="actions">
						<g:actionSubmit class="btn primary"
							value="${message(code: 'short.upload.label')}" action="save"/>
					</fieldset>

				</g:form>

			</div>
		</div>
	</div>
</body>
</html>
