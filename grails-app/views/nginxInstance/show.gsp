
<%@ page import="com.xtuple.NginxInstance" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'nginxInstance.label', default: 'NginxInstance')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-nginxInstance" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-nginxInstance" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list nginxInstance">
			
				<g:if test="${nginxInstanceInstance?.sudoPass}">
				<li class="fieldcontain">
					<span id="sudoPass-label" class="property-label"><g:message code="nginxInstance.sudoPass.label" default="Sudo Pass" /></span>
					
						<span class="property-value" aria-labelledby="sudoPass-label"><g:fieldValue bean="${nginxInstanceInstance}" field="sudoPass"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${nginxInstanceInstance?.aptPackages}">
				<li class="fieldcontain">
					<span id="aptPackages-label" class="property-label"><g:message code="nginxInstance.aptPackages.label" default="Apt Packages" /></span>
					
						<span class="property-value" aria-labelledby="aptPackages-label"><g:fieldValue bean="${nginxInstanceInstance}" field="aptPackages"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${nginxInstanceInstance?.host}">
				<li class="fieldcontain">
					<span id="host-label" class="property-label"><g:message code="nginxInstance.host.label" default="Host" /></span>
					
						<span class="property-value" aria-labelledby="host-label"><g:fieldValue bean="${nginxInstanceInstance}" field="host"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${nginxInstanceInstance?.identity}">
				<li class="fieldcontain">
					<span id="identity-label" class="property-label"><g:message code="nginxInstance.identity.label" default="Identity" /></span>
					
						<span class="property-value" aria-labelledby="identity-label"><g:fieldValue bean="${nginxInstanceInstance}" field="identity"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${nginxInstanceInstance?.instanceId}">
				<li class="fieldcontain">
					<span id="instanceId-label" class="property-label"><g:message code="nginxInstance.instanceId.label" default="Instance Id" /></span>
					
						<span class="property-value" aria-labelledby="instanceId-label"><g:fieldValue bean="${nginxInstanceInstance}" field="instanceId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${nginxInstanceInstance?.loginUser}">
				<li class="fieldcontain">
					<span id="loginUser-label" class="property-label"><g:message code="nginxInstance.loginUser.label" default="Login User" /></span>
					
						<span class="property-value" aria-labelledby="loginUser-label"><g:fieldValue bean="${nginxInstanceInstance}" field="loginUser"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${nginxInstanceInstance?.sshPort}">
				<li class="fieldcontain">
					<span id="sshPort-label" class="property-label"><g:message code="nginxInstance.sshPort.label" default="Ssh Port" /></span>
					
						<span class="property-value" aria-labelledby="sshPort-label"><g:fieldValue bean="${nginxInstanceInstance}" field="sshPort"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${nginxInstanceInstance?.zone}">
				<li class="fieldcontain">
					<span id="zone-label" class="property-label"><g:message code="nginxInstance.zone.label" default="Zone" /></span>
					
						<span class="property-value" aria-labelledby="zone-label"><g:link controller="zone" action="show" id="${nginxInstanceInstance?.zone?.id}">${nginxInstanceInstance?.zone?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:nginxInstanceInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${nginxInstanceInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
