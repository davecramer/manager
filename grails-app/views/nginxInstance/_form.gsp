<%@ page import="com.xtuple.NginxInstance" %>






<div class="fieldcontain ${hasErrors(bean: nginxInstanceInstance, field: 'host', 'error')} ">
	<label for="host">
		<g:message code="nginxInstance.host.label" default="Host" />
		
	</label>
	<g:textField name="host" value="${nginxInstanceInstance?.host}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: nginxInstanceInstance, field: 'identity', 'error')} ">
	<label for="identity">
		<g:message code="nginxInstance.identity.label" default="Identity" />
		
	</label>
	<g:textField name="identity" value="${nginxInstanceInstance?.identity}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: nginxInstanceInstance, field: 'instanceId', 'error')} ">
	<label for="instanceId">
		<g:message code="nginxInstance.instanceId.label" default="Instance Id" />
		
	</label>
	<g:textField name="instanceId" value="${nginxInstanceInstance?.instanceId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: nginxInstanceInstance, field: 'loginUser', 'error')} ">
	<label for="loginUser">
		<g:message code="nginxInstance.loginUser.label" default="Login User" />
		
	</label>
	<g:textField name="loginUser" value="${nginxInstanceInstance?.loginUser}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: nginxInstanceInstance, field: 'ipAddress', 'error')} ">
  <label for="ipAddress">
    <g:message code="nginxInstance.ipAddress.label" default="IP Address" />

  </label>
  <g:textField name="ipAddress" value="${nginxInstanceInstance?.ipAddress}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: nginxInstanceInstance, field: 'sshPort', 'error')} required">
	<label for="sshPort">
		<g:message code="nginxInstance.sshPort.label" default="Ssh Port" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="sshPort" type="number" value="${nginxInstanceInstance.sshPort}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: nginxInstanceInstance, field: 'zone', 'error')} required">
	<label for="zone">
		<g:message code="nginxInstance.zone.label" default="Zone" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="zone" name="zone.id" from="${com.xtuple.Zone.list()}" optionKey="id" required="" value="${nginxInstanceInstance?.zone?.id}" class="many-to-one"/>
</div>

