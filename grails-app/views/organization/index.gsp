
<%@ page import="com.xtuple.Organization" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'organization.label', default: 'Organization')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-organization" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-organization" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>

                      <g:sortableColumn property="name" title="${message(code: 'organization.name.label', default: 'Name')}" />

						<th><g:message code="organization.databaseServer.label" default="Database Server" /></th>
					
						<th><g:message code="organization.mobileServer.label" default="Mobile Server" /></th>
					

						<th><g:message code="organization.zone.label" default="Zone" /></th>

                        <g:sortableColumn property="active" title="${message(code: 'organization.active.label', default: 'Active')}" />

					</tr>
				</thead>
				<tbody>
				<g:each in="${organizationInstanceList}" status="i" var="organizationInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                      <td>${fieldValue(bean: organizationInstance, field: "name")}</td>

						<td>${fieldValue(bean: organizationInstance, field: "databaseServer")}</td>
					
						<td>${fieldValue(bean: organizationInstance, field: "mobileServer")}</td>
					

						<td>${fieldValue(bean: organizationInstance, field: "zone")}</td>
                        <td><g:link action="show" id="${organizationInstance.id}">${fieldValue(bean: organizationInstance, field: "active")}</g:link></td>

					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${organizationInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
