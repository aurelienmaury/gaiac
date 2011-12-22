<div class="clearfix ${hasErrors(bean: gaiacFileInstance, field: 'name', 'error')} required">
    <label for="name">
        <g:message code="gaiacFile.name.label" default="Name"/>
        <span class="required-indicator">*</span>
    </label>

    <div class="input">
        <g:textField name="name" required="" value="${gaiacFileInstance?.name}"/>
    </div>
</div>

<div class="clearfix">
    <label for="categories">
        <g:message code="gaiacFile.categories.label" default="Categories"/>
    </label>

    <div class="input">
        <g:select name="categories"
                  from="${categories}"
                  value="${gaiacFileInstance?.categories*.id}"
                  optionKey="id"
                  optionValue="name"
                  class="normalSelect"
                  multiple="true"/>
    </div>
</div>

<sec:ifAllGranted roles="ROLE_ADMIN">

    <div class="clearfix">
        <label for="name">
            <g:message code="gaiacFile.path.label"
                       default="Path"/>
        </label>

        <div class="input">
            <g:textField name="path"
                         required=""
                         value="${gaiacFileInstance?.path}"
                         class="disabled"
                         disabled="true"/>
        </div>
    </div>

  <div class="clearfix">
    <label for="exturl">
      <g:message code="gaiacFile.exturl.label"
                 default="External URL"/>
    </label>

    <div class="input">
      <g:textField name="exturl"
                   value="${gaiacFileInstance?.exturl}"/>
    </div>
  </div>

</sec:ifAllGranted>

