<%@page language="java" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.technologyRecord.form.label.title" path="title"/>
	<acme:form-textbox code="authenticated.technologyRecord.form.label.activitySector" path="activitySector"/>
	<acme:form-textbox code="authenticated.technologyRecord.form.label.inventorName" path="inventorName"/>
	<acme:form-textarea code="authenticated.technologyRecord.form.label.description" path="description"/>
	<acme:form-url code="authenticated.technologyRecord.form.label.website" path="website"/>
	<acme:form-textbox code="authenticated.technologyRecord.form.label.email" path="email"/>
	<acme:form-textbox code="authenticated.technologyRecord.form.label.openSource" path="openSource"/>
	<acme:form-textbox code="authenticated.technologyRecord.form.label.stars" path="stars"/>
	<acme:form-return code="authenticated.technologyRecord.form.button.return"/>
</acme:form>

