<div class="clearfix ${hasErrors(bean: memberInstance, field: 'email', 'error')} required">
  <label for="email">
    <g:message code="member.email.label" default="Email" />
    <span class="required-indicator">*</span>
  </label>
  <div class="input">
    <g:textField name="email" required="" value="${memberInstance?.email}"/>
  </div>
</div>

<div class="clearfix ${hasErrors(bean: memberInstance, field: 'password', 'error')} required">
  <label for="password">
    <g:message code="member.password.label" default="Password" />
    <span class="required-indicator">*</span>
  </label>
  <div class="input">
    <g:passwordField name="password" required="" value="${memberInstance?.password}"/>
  </div>
</div>

<div class="clearfix ${hasErrors(bean: memberInstance, field: 'password', 'error')} required">
  <label for="confirm">
    <g:message code="member.confirm.password.label" default="Confirm password" />
    <span class="required-indicator">*</span>
  </label>
  <div class="input">
    <g:passwordField name="confirmPassword" required="" value="${memberInstance?.password}"/>
  </div>
</div>

<div class="clearfix ${roleError?'error':''}">
  <label for="roles">
    <g:message code="member.roles.label" default="Roles" />
  </label>
  <div class="input">
    <ul class="inputs-list">
      <g:each var="oneRole" in="${rolesList}">
      <li>
        <label><g:checkBox name="roles" value="${oneRole.id}" checked="${checkRoles[oneRole.id]}"/><span>${oneRole.authority}</span></label>
      </li>
      </g:each>
    </ul>
  </div>
</div>

<div class="clearfix ${hasErrors(bean: memberInstance, field: 'accountExpired', 'error')} ">
  <label for="accountExpired">
    <g:message code="member.accountExpired.label" default="Account Expired" />
  </label>
  <div class="input">
    <g:checkBox name="accountExpired" value="${memberInstance?.accountExpired}" />
  </div>
</div>

<div class="clearfix ${hasErrors(bean: memberInstance, field: 'accountLocked', 'error')} ">
  <label for="accountLocked">
    <g:message code="member.accountLocked.label" default="Account Locked" />
    
  </label>
  <div class="input">
    <g:checkBox name="accountLocked" value="${memberInstance?.accountLocked}" />
  </div>
</div>

<div class="clearfix ${hasErrors(bean: memberInstance, field: 'enabled', 'error')} ">
  <label for="enabled">
    <g:message code="member.enabled.label" default="Enabled" />
  </label>
  <div class="input">
    <g:checkBox name="enabled" value="${memberInstance?.enabled}" />
  </div>
</div>

<div class="clearfix ${hasErrors(bean: memberInstance, field: 'passwordExpired', 'error')} ">
  <label for="passwordExpired">
    <g:message code="member.passwordExpired.label" default="Password Expired" />
  </label>
  <div class="input">
    <g:checkBox name="passwordExpired" value="${memberInstance?.passwordExpired}" />
  </div>
</div>