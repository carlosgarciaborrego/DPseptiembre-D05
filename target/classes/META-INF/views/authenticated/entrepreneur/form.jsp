<%@page language="java" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form>
	<acme:form-textbox code="authenticated.entrepreneur.form.label.startUpName" path="startUpName"/>
	<acme:form-textbox code="authenticated.entrepreneur.form.label.activitySector" path="activitySector"/>
	<acme:form-textbox code="authenticated.entrepreneur.form.label.qualificationRecord" path="qualificationRecord"/>
	<acme:form-textbox code="authenticated.entrepreneur.form.label.skillRecord" path="skillRecord"/>
	<acme:form-return code="authenticated.entrepreneur.form.button.return"/>
	
	<acme:form-submit test="${command == 'create'}" code="authenticated.entrepreneur.form.button.create" action="/authenticated/entrepreneur/create"/>
	<acme:form-submit test="${command == 'update'}" code="authenticated.entrepreneur.form.button.update" action="/authenticated/entrepreneur/update"/>
	
</acme:form>

