<%@ page import="com.xtuple.NatInstance" %>




<div class="fieldcontain ${hasErrors(bean: natInstanceInstance, field: 'host', 'error')} ">
	<label for="host">
		<g:message code="natInstance.host.label" default="Host" />
		
	</label>
	<g:textField name="host" value="${natInstanceInstance?.host}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: natInstanceInstance, field: 'identity', 'error')} ">
	<label for="identity">
		<g:message code="natInstance.identity.label" default="Identity" />
		
	</label>
	<g:textField name="identity" value="${natInstanceInstance?.identity}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: natInstanceInstance, field: 'instanceId', 'error')} ">
	<label for="instanceId">
		<g:message code="natInstance.instanceId.label" default="Instance Id" />
		
	</label>
	<g:textField name="instanceId" value="${natInstanceInstance?.instanceId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: natInstanceInstance, field: 'loginUser', 'error')} ">
	<label for="loginUser">
		<g:message code="natInstance.loginUser.label" default="Login User" />
		
	</label>
	<g:textField name="loginUser" value="${natInstanceInstance?.loginUser}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: natInstanceInstance, field: 'ipAddress', 'error')} ">
  <label for="ipAddress">
    <g:message code="natInstance.ipAddress.label" default="IP Address" />

  </label>
  <g:textField name="ipAddress" value="${natInstanceInstance?.ipAddress}"/>
</div>


<div class="fieldcontain ${hasErrors(bean: natInstanceInstance, field: 'natRules', 'error')} ">
	<label for="natRules">
		<g:message code="natInstance.natRules.label" default="Nat Rules" />
		
	</label>
	<g:select name="natRules" from="${com.xtuple.NatRule.list()}" multiple="multiple" optionKey="id" size="5" value="${natInstanceInstance?.natRules*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: natInstanceInstance, field: 'sshPort', 'error')} required">
	<label for="sshPort">
		<g:message code="natInstance.sshPort.label" default="Ssh Port" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="sshPort" type="number" value="${natInstanceInstance.sshPort}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: natInstanceInstance, field: 'zone', 'error')} required">
	<label for="zone">
		<g:message code="natInstance.zone.label" default="Zone" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="zone" name="zone.id" from="${com.xtuple.Zone.list()}" optionKey="id" required="" value="${natInstanceInstance?.zone?.id}" class="many-to-one"/>
</div>

