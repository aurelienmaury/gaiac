<div class="clearfix ${hasErrors(bean: gaiacFileInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="gaiacFile.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<div class="input">
	<g:textField name="name" required="" value="${gaiacFileInstance?.name}"/>
	</div>
</div>

<sec:ifAllGranted roles="ADMIN">
<div class="clearfix">
	<label for="name">
		<g:message code="gaiacFile.path.label" default="Path" />
	</label>
	<div class="input">
		<g:textField name="path" required="" value="${gaiacFileInstance?.path}" class="disabled" disabled="true"/>
	</div>
</div>
</sec:ifAllGranted>

