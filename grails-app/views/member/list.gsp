
<%@ page import="org.gaiac.domain.Member" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'member.label', default: 'Member')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
  </head>
  <body>
    <div class="content">
      
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
          
            <g:sortableColumn property="enabled" title="${message(code: 'member.enabled.label', default: 'Enabled')}" />

            <g:sortableColumn property="accountLocked" title="${message(code: 'member.accountLocked.label', default: 'Locked')}" />
          
            <g:sortableColumn property="accountExpired" title="${message(code: 'member.accountExpired.label', default: 'Expired')}" />

            <th class="w2 ctxt">${message(code: 'member.role.label', default: 'Roles')}</th>

          </tr>
        </thead>
        <tbody>
        <g:each in="${memberInstanceList}" status="i" var="memberInstance">
          <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
          
            <td><g:link action="show" id="${memberInstance.id}">${fieldValue(bean: memberInstance, field: "email")}</g:link></td>
          
            <td><img src="${resource(dir:'images',file: memberInstance.enabled?'tick_16.png':'cross_16.png')}"/></td>

            <td><img src="${resource(dir:'images',file: memberInstance.accountLocked?'tick_16.png':'cross_16.png')}"/></td>

            <td><img src="${resource(dir:'images',file: memberInstance.accountExpired?'tick_16.png':'cross_16.png')}"/></td>

            <td>${memberInstance.authorities*.authority.join(', ')}</td>

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
    <r:script>
$(document).ready(function() {
  $('#topbar-members').addClass('active');
});
</r:script>
  </body>
</html>
