
<%@ page import="com.xtuple.NatInstance" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'natInstance.label', default: 'NatInstance')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-natInstance" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-natInstance" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list natInstance">
			


			
				<g:if test="${natInstanceInstance?.host}">
				<li class="fieldcontain">
					<span id="host-label" class="property-label"><g:message code="natInstance.host.label" default="Host" /></span>
					
						<span class="property-value" aria-labelledby="host-label"><g:fieldValue bean="${natInstanceInstance}" field="host"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${natInstanceInstance?.identity}">
				<li class="fieldcontain">
					<span id="identity-label" class="property-label"><g:message code="natInstance.identity.label" default="Identity" /></span>
					
						<span class="property-value" aria-labelledby="identity-label"><g:fieldValue bean="${natInstanceInstance}" field="identity"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${natInstanceInstance?.instanceId}">
				<li class="fieldcontain">
					<span id="instanceId-label" class="property-label"><g:message code="natInstance.instanceId.label" default="Instance Id" /></span>
					
						<span class="property-value" aria-labelledby="instanceId-label"><g:fieldValue bean="${natInstanceInstance}" field="instanceId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${natInstanceInstance?.loginUser}">
				<li class="fieldcontain">
					<span id="loginUser-label" class="property-label"><g:message code="natInstance.loginUser.label" default="Login User" /></span>
					
						<span class="property-value" aria-labelledby="loginUser-label"><g:fieldValue bean="${natInstanceInstance}" field="loginUser"/></span>
					
				</li>
				</g:if>
              <g:if test="${natInstanceInstance?.ipAddress}">
                <li class="fieldcontain">
                  <span id="ipAddress-label" class="property-label"><g:message code="natInstance.ipAddress.label" default="IP Address" /></span>

                  <span class="property-value" aria-labelledby="ipAddress-label"><g:fieldValue bean="${natInstanceInstance}" field="ipAddress"/></span>

                </li>
              </g:if>

				<g:if test="${natInstanceInstance?.natRules}">
				<li class="fieldcontain">
					<span id="natRules-label" class="property-label"><g:message code="natInstance.natRules.label" default="Nat Rules" /></span>
					
						<g:each in="${natInstanceInstance.natRules}" var="n">
						<span class="property-value" aria-labelledby="natRules-label"><g:link controller="natRule" action="show" id="${n.id}">${n?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${natInstanceInstance?.sshPort}">
				<li class="fieldcontain">
					<span id="sshPort-label" class="property-label"><g:message code="natInstance.sshPort.label" default="Ssh Port" /></span>
					
						<span class="property-value" aria-labelledby="sshPort-label"><g:fieldValue bean="${natInstanceInstance}" field="sshPort"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${natInstanceInstance?.zone}">
				<li class="fieldcontain">
					<span id="zone-label" class="property-label"><g:message code="natInstance.zone.label" default="Zone" /></span>
					
						<span class="property-value" aria-labelledby="zone-label"><g:link controller="zone" action="show" id="${natInstanceInstance?.zone?.id}">${natInstanceInstance?.zone?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:natInstanceInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${natInstanceInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
