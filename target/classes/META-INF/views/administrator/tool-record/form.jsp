<%@page language="java" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form>
	<acme:form-textbox code="administrator.technologyTool.form.label.title" path="title"/>
	<acme:form-textbox code="administrator.technologyTool.form.label.activitySector" path="activitySector"/>
	<acme:form-textbox code="administrator.technologyTool.form.label.inventorName" path="inventorName"/>
	<acme:form-textarea code="administrator.technologyTool.form.label.description" path="description"/>
	<acme:form-url code="administrator.technologyTool.form.label.website" path="website"/>
	<acme:form-textbox code="administrator.technologyTool.form.label.email" path="email"/>
	<acme:form-checkbox code="administrator.technologyTool.form.label.openSource" path="openSource"/>
	<acme:form-textbox code="administrator.technologyTool.form.label.stars" path="stars"/>
	<acme:form-return code="administrator.technologyTool.form.button.return"/>
	
	<acme:form-submit test="${command == 'show'}" code="administrator.technologyTool.form.button.update" action="/administrator/tool-record/update"/>
	<acme:form-submit test="${command == 'show'}" code="administrator.technologyTool.form.button.delete" action="/administrator/tool-record/delete"/>
	<acme:form-submit test="${command == 'create'}" code="administrator.technologyTool.form.button.create" action="/administrator/tool-record/create"/>
	<acme:form-submit test="${command == 'update'}" code="administrator.technologyTool.form.button.update" action="/administrator/tool-record/update"/>
	<acme:form-submit test="${command == 'delete'}" code="administrator.technologyTool.form.button.delete" action="/administrator/tool-record/delete"/>
	
</acme:form>

