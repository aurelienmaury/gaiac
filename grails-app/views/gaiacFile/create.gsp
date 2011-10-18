<html>

<head>
	<meta name="layout" content="main">
</head>

<body>
	<div class="content">
		<div class="page-header">
			<h1><g:message code='gaiacFile.label' default='GaiacFile' /></h1>
		</div>
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
							<g:message code="short.list.label" />
						</g:link>
					</li>
					<li class="active"><g:link action="create">
							<g:message code="short.upload.label" />
						</g:link>
					</li>
				</ul>

				<g:form action="save" enctype="multipart/form-data">
					<fieldset class="form">
						<div class="clearfix">
							<label for="actualFile"><g:message code="short.upload.label"/> </label>
							<div class="input">
								<input type="file" name="uploadList"  multiple="multiple" required=""/>
							</div>
						</div>
					</fieldset>
					<fieldset class="actions">
						<g:submitButton name="create" class="btn primary"
							value="${message(code: 'short.upload.label')}" />
					</fieldset>
				</g:form>
			</div>
		</div>
	</div>
<r:script>
$(document).ready(function() {
  $('#topbar-search').addClass('active');
});
</r:script>
</body>
</html>
