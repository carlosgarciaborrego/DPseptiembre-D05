<%@page language="java" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form readonly="true">

	<acme:form-textbox code="authenticated.activity.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.activity.form.label.startDate" path="startDate" />
	<acme:form-moment code="authenticated.activity.form.label.endDate" path="endDate" />
	<acme:form-money code="authenticated.activity.form.label.budget" path="budget" />
	
	<acme:form-return code="authenticated.activity.form.button.return"/>
	
	
</acme:form>

 