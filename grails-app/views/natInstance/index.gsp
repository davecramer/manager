<%@ page import="com.xtuple.NatInstance" %>
<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="main">
  <g:set var="entityName" value="${message(code: 'natInstance.label', default: 'NatInstance')}"/>
  <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-natInstance" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                  default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
  <ul>
    <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
    <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></li>
  </ul>
</div>

<div id="list-natInstance" class="content scaffold-list" role="main">
  <h1><g:message code="default.list.label" args="[entityName]"/></h1>
  <g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
  </g:if>
  <table>
    <thead>
    <tr>

      <g:sortableColumn property="sudoPass"
                        title="${message(code: 'natInstance.sudoPass.label', default: 'Sudo Pass')}"/>

      <g:sortableColumn property="host" title="${message(code: 'natInstance.host.label', default: 'Host')}"/>

      <g:sortableColumn property="identity"
                        title="${message(code: 'natInstance.identity.label', default: 'Identity')}"/>

      <g:sortableColumn property="instanceId"
                        title="${message(code: 'natInstance.instanceId.label', default: 'Instance Id')}"/>

      <g:sortableColumn property="loginUser"
                        title="${message(code: 'natInstance.loginUser.label', default: 'Login User')}"/>

      <g:sortableColumn property="sshPort" title="${message(code: 'natInstance.sshPort.label', default: 'Ssh Port')}"/>

    </tr>
    </thead>
    <tbody>
    <g:each in="${natInstanceInstanceList}" status="i" var="natInstanceInstance">
      <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

        <td><g:link action="show"
                    id="${natInstanceInstance.id}">${fieldValue(bean: natInstanceInstance, field: "sudoPass")}</g:link></td>

        <td>${fieldValue(bean: natInstanceInstance, field: "host")}</td>

        <td>${fieldValue(bean: natInstanceInstance, field: "identity")}</td>

        <td>${fieldValue(bean: natInstanceInstance, field: "instanceId")}</td>

        <td>${fieldValue(bean: natInstanceInstance, field: "loginUser")}</td>

        <td>${fieldValue(bean: natInstanceInstance, field: "sshPort")}</td>

      </tr>
    </g:each>
    </tbody>
  </table>

  <div class="pagination">
    <g:paginate total="${natInstanceInstanceCount ?: 0}"/>
  </div>
</div>
</body>
</html>
