
<%@ page import="com.xtuple.NginxInstance" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'nginxInstance.label', default: 'NginxInstance')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-nginxInstance" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-nginxInstance" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="ipAddress" title="${message(code: 'nginxInstance.ipAddress.label', default: 'IP Address')}" />
					
						<g:sortableColumn property="host" title="${message(code: 'nginxInstance.host.label', default: 'Host')}" />
					
						<g:sortableColumn property="identity" title="${message(code: 'nginxInstance.identity.label', default: 'Identity')}" />
					
						<g:sortableColumn property="instanceId" title="${message(code: 'nginxInstance.instanceId.label', default: 'Instance Id')}" />
					
						<g:sortableColumn property="loginUser" title="${message(code: 'nginxInstance.loginUser.label', default: 'Login User')}" />
					
						<g:sortableColumn property="sshPort" title="${message(code: 'nginxInstance.sshPort.label', default: 'Ssh Port')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${nginxInstanceInstanceList}" status="i" var="nginxInstanceInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${nginxInstanceInstance.id}">${fieldValue(bean: nginxInstanceInstance, field: "ipAddress")}</g:link></td>
					
						<td>${fieldValue(bean: nginxInstanceInstance, field: "host")}</td>
					
						<td>${fieldValue(bean: nginxInstanceInstance, field: "identity")}</td>
					
						<td>${fieldValue(bean: nginxInstanceInstance, field: "instanceId")}</td>
					
						<td>${fieldValue(bean: nginxInstanceInstance, field: "loginUser")}</td>
					
						<td>${fieldValue(bean: nginxInstanceInstance, field: "sshPort")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${nginxInstanceInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
