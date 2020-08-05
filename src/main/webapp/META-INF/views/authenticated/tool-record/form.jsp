<%@page language="java" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.technologyTool.form.label.title" path="title"/>
	<acme:form-textbox code="authenticated.technologyTool.form.label.activitySector" path="activitySector"/>
	<acme:form-textbox code="authenticated.technologyTool.form.label.inventorName" path="inventorName"/>
	<acme:form-textarea code="authenticated.technologyTool.form.label.description" path="description"/>
	<acme:form-url code="authenticated.technologyTool.form.label.website" path="website"/>
	<acme:form-textbox code="authenticated.technologyTool.form.label.email" path="email"/>
	<acme:form-textbox code="authenticated.technologyTool.form.label.openSource" path="openSource"/>
	<acme:form-textbox code="authenticated.technologyTool.form.label.stars" path="stars"/>
	<acme:form-return code="authenticated.technologyTool.form.button.return"/>
</acme:form>

