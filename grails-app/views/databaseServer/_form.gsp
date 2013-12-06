<%@ page import="com.xtuple.DatabaseServer" %>



<div class="fieldcontain ${hasErrors(bean: databaseServerInstance, field: 'host', 'error')} ">
	<label for="host">
		<g:message code="databaseServer.host.label" default="Host" />
		
	</label>
	<g:textField name="host" value="${databaseServerInstance?.host}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: databaseServerInstance, field: 'identity', 'error')} ">
	<label for="identity">
		<g:message code="databaseServer.identity.label" default="Identity" />
		
	</label>
	<g:textField name="identity" value="${databaseServerInstance?.identity}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: databaseServerInstance, field: 'instanceId', 'error')} ">
	<label for="instanceId">
		<g:message code="databaseServer.instanceId.label" default="Instance Id" />
		
	</label>
	<g:textField name="instanceId" value="${databaseServerInstance?.instanceId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: databaseServerInstance, field: 'loginUser', 'error')} ">
	<label for="loginUser">
		<g:message code="databaseServer.loginUser.label" default="Login User" />
		
	</label>
	<g:textField name="loginUser" value="${databaseServerInstance?.loginUser}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: databaseServerInstance, field: 'ipAddress', 'error')} ">
  <label for="ipAddress">
    <g:message code="databaseServer.ipAddress.label" default="IP Address" />

  </label>
  <g:textField name="ipAddress" value="${databaseServerInstance?.ipAddress}"/>
</div>


<div class="fieldcontain ${hasErrors(bean: databaseServerInstance, field: 'master', 'error')} ">
	<label for="master">
		<g:message code="databaseServer.master.label" default="Master" />
		
	</label>
	<g:checkBox name="master" value="${databaseServerInstance?.master}" />
</div>

<div class="fieldcontain ${hasErrors(bean: databaseServerInstance, field: 'organizations', 'error')} ">
	<label for="organizations">
		<g:message code="databaseServer.organizations.label" default="Organizations" />
		
	</label>
	<g:select name="organizations" from="${com.xtuple.Organization.list()}" multiple="multiple" optionKey="id" size="5" value="${databaseServerInstance?.organizations*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: databaseServerInstance, field: 'sshPort', 'error')} required">
	<label for="sshPort">
		<g:message code="databaseServer.sshPort.label" default="Ssh Port" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="sshPort" type="number" value="${databaseServerInstance.sshPort}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: databaseServerInstance, field: 'zone', 'error')} required">
	<label for="zone">
		<g:message code="databaseServer.zone.label" default="Zone" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="zone" name="zone.id" from="${com.xtuple.Zone.list()}" optionKey="id" required="" value="${databaseServerInstance?.zone?.id}" class="many-to-one"/>
</div>

