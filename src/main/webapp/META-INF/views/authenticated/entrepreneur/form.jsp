<%@page language="java" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.entrepreneur.form.label.startUpName" path="startUpName"/>
	<acme:form-textbox code="authenticated.entrepreneur.form.label.activitySector" path="activitySector"/>
	<acme:form-textbox code="authenticated.entrepreneur.form.label.qualificationRecord" path="qualificationRecord"/>
	<acme:form-textbox code="authenticated.entrepreneur.form.label.skillRecord" path="skillRecord"/>
	<acme:form-return code="authenticated.entrepreneur.form.button.return"/>
</acme:form>

