
<%@ page import="com.xtuple.DatabaseServer" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'databaseServer.label', default: 'DatabaseServer')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-databaseServer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-databaseServer" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="instanceId" title="${message(code: 'databaseServer.instanceId.label', default: 'Instance Id')}" />
					
						<g:sortableColumn property="host" title="${message(code: 'databaseServer.host.label', default: 'Host')}" />
					
						<g:sortableColumn property="identity" title="${message(code: 'databaseServer.identity.label', default: 'Identity')}" />
					
						<g:sortableColumn property="loginUser" title="${message(code: 'databaseServer.loginUser.label', default: 'Login User')}" />
					
						<g:sortableColumn property="master" title="${message(code: 'databaseServer.master.label', default: 'Master')}" />
					
						<g:sortableColumn property="sshPort" title="${message(code: 'databaseServer.sshPort.label', default: 'SSH Port')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${databaseServerInstanceList}" status="i" var="databaseServerInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${databaseServerInstance.id}">${fieldValue(bean: databaseServerInstance, field: "instanceId")}</g:link></td>
					
						<td>${fieldValue(bean: databaseServerInstance, field: "host")}</td>
					
						<td>${fieldValue(bean: databaseServerInstance, field: "identity")}</td>
					
						<td>${fieldValue(bean: databaseServerInstance, field: "loginUser")}</td>
					
						<td><g:formatBoolean boolean="${databaseServerInstance.master}" /></td>
					
						<td>${databaseServerInstance.sshPort}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${databaseServerInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
