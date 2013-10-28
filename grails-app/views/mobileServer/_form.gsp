<%@ page import="com.xtuple.MobileServer" %>



<div class="fieldcontain ${hasErrors(bean: mobileServerInstance, field: 'sudoPass', 'error')} ">
	<label for="sudoPass">
		<g:message code="mobileServer.sudoPass.label" default="Sudo Pass" />
		
	</label>
	<g:textField name="sudoPass" value="${mobileServerInstance?.sudoPass}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: mobileServerInstance, field: 'aptPackages', 'error')} ">
	<label for="aptPackages">
		<g:message code="mobileServer.aptPackages.label" default="Apt Packages" />
		
	</label>
	
</div>

<div class="fieldcontain ${hasErrors(bean: mobileServerInstance, field: 'databaseServer', 'error')} required">
	<label for="databaseServer">
		<g:message code="mobileServer.databaseServer.label" default="Database Server" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="databaseServer" name="databaseServer.id" from="${com.xtuple.DatabaseServer.list()}" optionKey="id" required="" value="${mobileServerInstance?.databaseServer?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: mobileServerInstance, field: 'host', 'error')} ">
	<label for="host">
		<g:message code="mobileServer.host.label" default="Host" />
		
	</label>
	<g:textField name="host" value="${mobileServerInstance?.host}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: mobileServerInstance, field: 'identity', 'error')} ">
	<label for="identity">
		<g:message code="mobileServer.identity.label" default="Identity" />
		
	</label>
	<g:textField name="identity" value="${mobileServerInstance?.identity}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: mobileServerInstance, field: 'instanceId', 'error')} ">
	<label for="instanceId">
		<g:message code="mobileServer.instanceId.label" default="Instance Id" />
		
	</label>
	<g:textField name="instanceId" value="${mobileServerInstance?.instanceId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: mobileServerInstance, field: 'loginUser', 'error')} ">
	<label for="loginUser">
		<g:message code="mobileServer.loginUser.label" default="Login User" />
		
	</label>
	<g:textField name="loginUser" value="${mobileServerInstance?.loginUser}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: mobileServerInstance, field: 'replica', 'error')} ">
	<label for="replica">
		<g:message code="mobileServer.replica.label" default="Replica" />
		
	</label>
	<g:checkBox name="replica" value="${mobileServerInstance?.replica}" />
</div>

<div class="fieldcontain ${hasErrors(bean: mobileServerInstance, field: 'replicas', 'error')} ">
	<label for="replicas">
		<g:message code="mobileServer.replicas.label" default="Replicas" />
		
	</label>
	<g:select name="replicas" from="${com.xtuple.MobileServer.list()}" multiple="multiple" optionKey="id" size="5" value="${mobileServerInstance?.replicas*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: mobileServerInstance, field: 'sshPort', 'error')} required">
	<label for="sshPort">
		<g:message code="mobileServer.sshPort.label" default="Ssh Port" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="sshPort" type="number" value="${mobileServerInstance.sshPort}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: mobileServerInstance, field: 'zone', 'error')} required">
	<label for="zone">
		<g:message code="mobileServer.zone.label" default="Zone" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="zone" name="zone.id" from="${com.xtuple.Zone.list()}" optionKey="id" required="" value="${mobileServerInstance?.zone?.id}" class="many-to-one"/>
</div>

