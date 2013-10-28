
<%@ page import="com.xtuple.Zone" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'zone.label', default: 'Zone')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-zone" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-zone" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>

                        <g:sortableColumn property="name" title="${message(code: 'zone.name.label', default: 'Name')}" />

                        <th><g:message code="zone.nginxInstance.label" default="Nginx Instance" /></th>

                        <th><g:message code="zone.natInstance.label" default="Nat Instance" /></th>
					
						<g:sortableColumn property="keyName" title="${message(code: 'zone.keyName.label', default: 'Key Name')}" />
					

					</tr>
				</thead>
				<tbody>
				<g:each in="${zoneInstanceList}" status="i" var="zoneInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${zoneInstance.id}">${fieldValue(bean: zoneInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: zoneInstance, field: "nginxInstance")}</td>

						<td>${fieldValue(bean: zoneInstance, field: "natInstance")}</td>

                        <td>${fieldValue(bean: zoneInstance, field: "keyName")}</td>

					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${zoneInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
