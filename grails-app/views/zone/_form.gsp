<%@ page import="com.xtuple.Zone" %>



<div class="fieldcontain ${hasErrors(bean: zoneInstance, field: 'natInstance', 'error')} ">
	<label for="natInstance">
		<g:message code="zone.natInstance.label" default="Nat Instance" />
		
	</label>
	<g:select id="natInstance" name="natInstance.id" from="${com.xtuple.NatInstance.list()}" optionKey="id" value="${zoneInstance?.natInstance?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: zoneInstance, field: 'nginxInstance', 'error')} ">
	<label for="nginxInstance">
		<g:message code="zone.nginxInstance.label" default="Nginx Instance" />
		
	</label>
	<g:select id="nginxInstance" name="nginxInstance.id" from="${com.xtuple.NginxInstance.list()}" optionKey="id" value="${zoneInstance?.nginxInstance?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: zoneInstance, field: 'databaseServers', 'error')} ">
	<label for="databaseServers">
		<g:message code="zone.databaseServers.label" default="Database Servers" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${zoneInstance?.databaseServers?}" var="d">
    <li><g:link controller="databaseServer" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="databaseServer" action="create" params="['zone.id': zoneInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'databaseServer.label', default: 'DatabaseServer')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: zoneInstance, field: 'keyName', 'error')} ">
	<label for="keyName">
		<g:message code="zone.keyName.label" default="Key Name" />
		
	</label>
	<g:textField name="keyName" value="${zoneInstance?.keyName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: zoneInstance, field: 'mobileServers', 'error')} ">
	<label for="mobileServers">
		<g:message code="zone.mobileServers.label" default="Mobile Servers" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${zoneInstance?.mobileServers?}" var="m">
    <li><g:link controller="mobileServer" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="mobileServer" action="create" params="['zone.id': zoneInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'mobileServer.label', default: 'MobileServer')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: zoneInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="zone.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${zoneInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: zoneInstance, field: 'organizations', 'error')} ">
	<label for="organizations">
		<g:message code="zone.organizations.label" default="Organizations" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${zoneInstance?.organizations?}" var="o">
    <li><g:link controller="organization" action="show" id="${o.id}">${o?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="organization" action="create" params="['zone.id': zoneInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'organization.label', default: 'Organization')])}</g:link>
</li>
</ul>

</div>

