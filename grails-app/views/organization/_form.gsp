<%@ page import="com.xtuple.Organization" %>



<div class="fieldcontain ${hasErrors(bean: organizationInstance, field: 'active', 'error')} ">
	<label for="active">
		<g:message code="organization.active.label" default="Active" />
		
	</label>
	<g:checkBox name="active" value="${organizationInstance?.active}" />
</div>

<div class="fieldcontain ${hasErrors(bean: organizationInstance, field: 'databaseServer', 'error')} required">
	<label for="databaseServer">
		<g:message code="organization.databaseServer.label" default="Database Server" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="databaseServer" name="databaseServer.id" from="${com.xtuple.DatabaseServer.list()}" optionKey="id" required="" value="${organizationInstance?.databaseServer?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: organizationInstance, field: 'mobileServer', 'error')} required">
	<label for="mobileServer">
		<g:message code="organization.mobileServer.label" default="Mobile Server" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="mobileServer" name="mobileServer.id" from="${com.xtuple.MobileServer.list()}" optionKey="id" required="" value="${organizationInstance?.mobileServer?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: organizationInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="organization.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${organizationInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: organizationInstance, field: 'zone', 'error')} required">
	<label for="zone">
		<g:message code="organization.zone.label" default="Zone" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="zone" name="zone.id" from="${com.xtuple.Zone.list()}" optionKey="id" required="" value="${organizationInstance?.zone?.id}" class="many-to-one"/>
</div>

