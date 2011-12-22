<%@ page import="org.gaiac.domain.Category" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'category.label', default: 'Category')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="content">

    <div class="row">
        <div class="span16">
            <ul class="pills">
                <li class="active"><a href="#"><g:message code="short.list.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="short.new.label"/></g:link></li>
            </ul>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <table>
                <thead>
                <tr>

                    <g:sortableColumn property="name" title="${message(code: 'category.name.label', default: 'Name')}"/>

                    <g:sortableColumn property="description"
                                      title="${message(code: 'category.description.label', default: 'Description')}"/>

                </tr>
                </thead>
                <tbody>
                <g:each in="${categoryInstanceList}" status="i" var="categoryInstance">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                        <td><g:link action="show"
                                    id="${categoryInstance.id}">${fieldValue(bean: categoryInstance, field: "name")}</g:link></td>

                        <td>${fieldValue(bean: categoryInstance, field: "description")}</td>

                    </tr>
                </g:each>
                </tbody>
            </table>

            <div class="pagination">
                <bs:paginate total="${categoryInstanceTotal}" params="[query: query]"/>

            </div>
        </div>
    </div>
</body>
</html>
