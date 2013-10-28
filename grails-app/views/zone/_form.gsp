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
	<g:select name="databaseServers" from="${com.xtuple.DatabaseServer.list()}" multiple="multiple" optionKey="id" size="5" value="${zoneInstance?.databaseServers*.id}" class="many-to-many"/>
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
	<g:select name="mobileServers" from="${com.xtuple.MobileServer.list()}" multiple="multiple" optionKey="id" size="5" value="${zoneInstance?.mobileServers*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: zoneInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="zone.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${zoneInstance?.name}"/>
</div>

