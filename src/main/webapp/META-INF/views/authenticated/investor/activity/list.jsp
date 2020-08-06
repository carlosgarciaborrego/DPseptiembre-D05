<%@page  language ="java"%>


<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:list>
	<acme:list-column code="authenticated.activity.form.label.title" path="title" width="25%"/>
	<acme:list-column code="authenticated.activity.form.label.startDate" path="startDate" width="25%"/>
	<acme:list-column code="authenticated.activity.form.label.endDate" path="endDate" width="25%"/>
	<acme:list-column code="authenticated.activity.form.label.budget" path="budget" width="25%"/>	
	
</acme:list>

<acme:form-return code="authenticated.activity.form.button.return"/>


