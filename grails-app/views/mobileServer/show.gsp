
<%@ page import="com.xtuple.MobileServer" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'mobileServer.label', default: 'MobileServer')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-mobileServer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-mobileServer" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list mobileServer">

              <g:if test="${mobileServerInstance?.instanceId}">
                <li class="fieldcontain">
                  <span id="instanceId-label" class="property-label"><g:message code="mobileServer.instanceId.label" default="Instance Id" /></span>

                  <span class="property-value" aria-labelledby="instanceId-label"><g:fieldValue bean="${mobileServerInstance}" field="instanceId"/></span>

                </li>
              </g:if>
			
				<g:if test="${mobileServerInstance?.databaseServer}">
				<li class="fieldcontain">
					<span id="databaseServer-label" class="property-label"><g:message code="mobileServer.databaseServer.label" default="Database Server" /></span>
					
						<span class="property-value" aria-labelledby="databaseServer-label"><g:link controller="databaseServer" action="show" id="${mobileServerInstance?.databaseServer?.id}">${mobileServerInstance?.databaseServer?.instanceId?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${mobileServerInstance?.host}">
				<li class="fieldcontain">
					<span id="host-label" class="property-label"><g:message code="mobileServer.host.label" default="Host" /></span>
					
						<span class="property-value" aria-labelledby="host-label"><g:fieldValue bean="${mobileServerInstance}" field="host"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${mobileServerInstance?.identity}">
				<li class="fieldcontain">
					<span id="identity-label" class="property-label"><g:message code="mobileServer.identity.label" default="Identity" /></span>
					
						<span class="property-value" aria-labelledby="identity-label"><g:fieldValue bean="${mobileServerInstance}" field="identity"/></span>
					
				</li>
				</g:if>
			

			
				<g:if test="${mobileServerInstance?.loginUser}">
				<li class="fieldcontain">
					<span id="loginUser-label" class="property-label"><g:message code="mobileServer.loginUser.label" default="Login User" /></span>
					
						<span class="property-value" aria-labelledby="loginUser-label"><g:fieldValue bean="${mobileServerInstance}" field="loginUser"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${mobileServerInstance?.replica}">
				<li class="fieldcontain">
					<span id="replica-label" class="property-label"><g:message code="mobileServer.replica.label" default="Replica" /></span>
					
						<span class="property-value" aria-labelledby="replica-label"><g:formatBoolean boolean="${mobileServerInstance?.replica}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${mobileServerInstance?.replicas}">
				<li class="fieldcontain">
					<span id="replicas-label" class="property-label"><g:message code="mobileServer.replicas.label" default="Replicas" /></span>
					
						<g:each in="${mobileServerInstance.replicas}" var="r">
						<span class="property-value" aria-labelledby="replicas-label"><g:link controller="mobileServer" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${mobileServerInstance?.sshPort}">
				<li class="fieldcontain">
					<span id="sshPort-label" class="property-label"><g:message code="mobileServer.sshPort.label" default="Ssh Port" /></span>
					
						<span class="property-value" aria-labelledby="sshPort-label"><g:fieldValue bean="${mobileServerInstance}" field="sshPort"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${mobileServerInstance?.zone}">
				<li class="fieldcontain">
					<span id="zone-label" class="property-label"><g:message code="mobileServer.zone.label" default="Zone" /></span>
					
						<span class="property-value" aria-labelledby="zone-label"><g:link controller="zone" action="show" id="${mobileServerInstance?.zone?.id}">${mobileServerInstance?.zone?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:mobileServerInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${mobileServerInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
