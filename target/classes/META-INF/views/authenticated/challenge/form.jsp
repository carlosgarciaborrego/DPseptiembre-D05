<%@page language="java" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.challenge.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.challenge.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="authenticated.challenge.form.label.description" path="description"/>
	<acme:form-textbox code="authenticated.challenge.form.label.goalRookie" path="goalRookie"/>
	<acme:form-money code="authenticated.challenge.form.label.rewardRookie" path="rewardRookie"/>
	<acme:form-textbox code="authenticated.challenge.form.label.goalAverage" path="goalAverage"/>
	<acme:form-money code="authenticated.challenge.form.label.rewardAverage" path="rewardAverage"/>
	<acme:form-textbox code="authenticated.challenge.form.label.goalExpert" path="goalExpert"/>
	<acme:form-money code="authenticated.challenge.form.label.rewardExpert" path="rewardExpert"/>
	<acme:form-return code="authenticated.challenge.form.button.return"/>
</acme:form>

