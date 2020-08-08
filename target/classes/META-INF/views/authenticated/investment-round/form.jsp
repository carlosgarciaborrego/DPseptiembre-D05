<%@page language="java" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form>
	<acme:form-textbox code="authenticated.investmentRound.form.label.ticker" path="ticker"/>
	<acme:form-moment code="authenticated.investmentRound.form.label.creation" path="creation"/>
	<acme:form-textbox code="authenticated.investmentRound.form.label.kindRound" path="kindRound"/>
	<acme:form-textbox code="authenticated.investmentRound.form.label.title" path="title"/>
	<acme:form-textbox code="authenticated.investmentRound.form.label.description" path="description"/>
	<acme:form-money code="authenticated.investmentRound.form.label.amount" path="amount"/>
	<acme:form-url code="authenticated.investmentRound.form.label.link" path="link"/>
	<acme:form-checkbox code="authenticated.investmentRound.form.label.active" path="active"/>
	<acme:form-return code="authenticated.investmentRound.form.button.return"/>
	
	<acme:form-submit code="authenticated.investmentRoundform.label.activity" action="/authenticated/activity/list?id=${id}" method="get"/>
	

</acme:form>

