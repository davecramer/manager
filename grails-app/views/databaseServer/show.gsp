
<%@ page import="com.xtuple.DatabaseServer" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'databaseServer.label', default: 'DatabaseServer')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-databaseServer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-databaseServer" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list databaseServer">


              <g:if test="${databaseServerInstance?.instanceId}">
                <li class="fieldcontain">
                  <span id="instanceId-label" class="property-label"><g:message code="databaseServer.instanceId.label" default="Instance Id" /></span>

                  <span class="property-value" aria-labelledby="instanceId-label"><g:fieldValue bean="${databaseServerInstance}" field="instanceId"/></span>

                </li>
              </g:if>
				<g:if test="${databaseServerInstance?.host}">
				<li class="fieldcontain">
					<span id="host-label" class="property-label"><g:message code="databaseServer.host.label" default="Host" /></span>
					
						<span class="property-value" aria-labelledby="host-label"><g:fieldValue bean="${databaseServerInstance}" field="host"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${databaseServerInstance?.identity}">
				<li class="fieldcontain">
					<span id="identity-label" class="property-label"><g:message code="databaseServer.identity.label" default="Identity" /></span>
					
						<span class="property-value" aria-labelledby="identity-label"><g:fieldValue bean="${databaseServerInstance}" field="identity"/></span>
					
				</li>
				</g:if>
			

			
				<g:if test="${databaseServerInstance?.loginUser}">
				<li class="fieldcontain">
					<span id="loginUser-label" class="property-label"><g:message code="databaseServer.loginUser.label" default="Login User" /></span>
					
						<span class="property-value" aria-labelledby="loginUser-label"><g:fieldValue bean="${databaseServerInstance}" field="loginUser"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${databaseServerInstance?.master}">
				<li class="fieldcontain">
					<span id="master-label" class="property-label"><g:message code="databaseServer.master.label" default="Master" /></span>
					
						<span class="property-value" aria-labelledby="master-label"><g:formatBoolean boolean="${databaseServerInstance?.master}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${databaseServerInstance?.organizations}">
				<li class="fieldcontain">
					<span id="organizations-label" class="property-label"><g:message code="databaseServer.organizations.label" default="Organizations" /></span>
					
						<g:each in="${databaseServerInstance.organizations}" var="o">
						<span class="property-value" aria-labelledby="organizations-label"><g:link controller="organization" action="show" id="${o.id}">${o?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${databaseServerInstance?.sshPort}">
				<li class="fieldcontain">
					<span id="sshPort-label" class="property-label"><g:message code="databaseServer.sshPort.label" default="Ssh Port" /></span>
					
						<span class="property-value" aria-labelledby="sshPort-label"><g:fieldValue bean="${databaseServerInstance}" field="sshPort"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${databaseServerInstance?.zone}">
				<li class="fieldcontain">
					<span id="zone-label" class="property-label"><g:message code="databaseServer.zone.label" default="Zone" /></span>
					
						<span class="property-value" aria-labelledby="zone-label"><g:link controller="zone" action="show" id="${databaseServerInstance?.zone?.id}">${databaseServerInstance?.zone?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:databaseServerInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${databaseServerInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
