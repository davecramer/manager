
<%@ page import="com.xtuple.Organization" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'organization.label', default: 'Organization')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-organization" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-organization" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list organization">
			
				<g:if test="${organizationInstance?.active}">
				<li class="fieldcontain">
					<span id="active-label" class="property-label"><g:message code="organization.active.label" default="Active" /></span>
					
						<span class="property-value" aria-labelledby="active-label"><g:formatBoolean boolean="${organizationInstance?.active}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${organizationInstance?.databaseServer}">
				<li class="fieldcontain">
					<span id="databaseServer-label" class="property-label"><g:message code="organization.databaseServer.label" default="Database Server" /></span>
					
						<span class="property-value" aria-labelledby="databaseServer-label"><g:link controller="databaseServer" action="show" id="${organizationInstance?.databaseServer?.id}">${organizationInstance?.databaseServer?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${organizationInstance?.mobileServer}">
				<li class="fieldcontain">
					<span id="mobileServer-label" class="property-label"><g:message code="organization.mobileServer.label" default="Mobile Server" /></span>
					
						<span class="property-value" aria-labelledby="mobileServer-label"><g:link controller="mobileServer" action="show" id="${organizationInstance?.mobileServer?.id}">${organizationInstance?.mobileServer?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${organizationInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="organization.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${organizationInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${organizationInstance?.zone}">
				<li class="fieldcontain">
					<span id="zone-label" class="property-label"><g:message code="organization.zone.label" default="Zone" /></span>
					
						<span class="property-value" aria-labelledby="zone-label"><g:link controller="zone" action="show" id="${organizationInstance?.zone?.id}">${organizationInstance?.zone?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:organizationInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${organizationInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
