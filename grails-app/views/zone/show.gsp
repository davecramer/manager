
<%@ page import="com.xtuple.Zone" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'zone.label', default: 'Zone')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-zone" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-zone" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list zone">
			
				<g:if test="${zoneInstance?.natInstance}">
				<li class="fieldcontain">
					<span id="natInstance-label" class="property-label"><g:message code="zone.natInstance.label" default="Nat Instance" /></span>
					
						<span class="property-value" aria-labelledby="natInstance-label"><g:link controller="natInstance" action="show" id="${zoneInstance?.natInstance?.id}">${zoneInstance?.natInstance?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${zoneInstance?.nginxInstance}">
				<li class="fieldcontain">
					<span id="nginxInstance-label" class="property-label"><g:message code="zone.nginxInstance.label" default="Nginx Instance" /></span>
					
						<span class="property-value" aria-labelledby="nginxInstance-label"><g:link controller="nginxInstance" action="show" id="${zoneInstance?.nginxInstance?.id}">${zoneInstance?.nginxInstance?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${zoneInstance?.databaseServers}">
				<li class="fieldcontain">
					<span id="databaseServers-label" class="property-label"><g:message code="zone.databaseServers.label" default="Database Servers" /></span>
					
						<g:each in="${zoneInstance.databaseServers}" var="d">
						<span class="property-value" aria-labelledby="databaseServers-label"><g:link controller="databaseServer" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${zoneInstance?.keyName}">
				<li class="fieldcontain">
					<span id="keyName-label" class="property-label"><g:message code="zone.keyName.label" default="Key Name" /></span>
					
						<span class="property-value" aria-labelledby="keyName-label"><g:fieldValue bean="${zoneInstance}" field="keyName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${zoneInstance?.mobileServers}">
				<li class="fieldcontain">
					<span id="mobileServers-label" class="property-label"><g:message code="zone.mobileServers.label" default="Mobile Servers" /></span>
					
						<g:each in="${zoneInstance.mobileServers}" var="m">
						<span class="property-value" aria-labelledby="mobileServers-label"><g:link controller="mobileServer" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${zoneInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="zone.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${zoneInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${zoneInstance?.id}" />
					<g:link class="edit" action="edit" id="${zoneInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
