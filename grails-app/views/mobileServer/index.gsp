<%@ page import="com.xtuple.MobileServer" %>
<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="main">
  <g:set var="entityName" value="${message(code: 'mobileServer.label', default: 'MobileServer')}"/>
  <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-mobileServer" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                   default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
  <ul>
    <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
    <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></li>
  </ul>
</div>

<div id="list-mobileServer" class="content scaffold-list" role="main">
  <h1><g:message code="default.list.label" args="[entityName]"/></h1>
  <g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
  </g:if>
  <table>
    <thead>
    <tr>

      <g:sortableColumn property="ipAddress"
                        title="${message(code: 'mobileServer.ipAddress.label', default: 'IP Address')}"/>

      <th><g:message code="mobileServer.databaseServer.label" default="Database Server"/></th>

      <g:sortableColumn property="host" title="${message(code: 'mobileServer.host.label', default: 'Host')}"/>

      <g:sortableColumn property="identity"
                        title="${message(code: 'mobileServer.identity.label', default: 'Identity')}"/>

      <g:sortableColumn property="instanceId"
                        title="${message(code: 'mobileServer.instanceId.label', default: 'Instance Id')}"/>

      <g:sortableColumn property="loginUser"
                        title="${message(code: 'mobileServer.loginUser.label', default: 'Login User')}"/>

    </tr>
    </thead>
    <tbody>
    <g:each in="${mobileServerInstanceList}" status="i" var="mobileServerInstance">
      <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

        <td><g:link action="show"
                    id="${mobileServerInstance.id}">${fieldValue(bean: mobileServerInstance, field: "ipAddress")}</g:link></td>

        <td>${fieldValue(bean: mobileServerInstance, field: "databaseServer")}</td>

        <td>${fieldValue(bean: mobileServerInstance, field: "host")}</td>

        <td>${fieldValue(bean: mobileServerInstance, field: "identity")}</td>

        <td>${fieldValue(bean: mobileServerInstance, field: "instanceId")}</td>

        <td>${fieldValue(bean: mobileServerInstance, field: "loginUser")}</td>

      </tr>
    </g:each>
    </tbody>
  </table>

  <div class="pagination">
    <g:paginate total="${mobileServerInstanceCount ?: 0}"/>
  </div>
</div>
</body>
</html>
