<%@ page import="org.gaiac.domain.Category" %>


<div class="clearfix ${hasErrors(bean: categoryInstance, field: 'name', 'error')} required">
  <label for="name">
		<g:message code="category.name.label" default="Name" />
    <span class="required-indicator">*</span>
  </label>
  <div class="input">
    <g:textField name="name" required="" value="${categoryInstance?.name}"/>
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

