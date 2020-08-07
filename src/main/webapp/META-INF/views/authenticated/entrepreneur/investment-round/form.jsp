<%@page language="java" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form>
	<acme:form-textbox code="authenticated.investmentRound.form.label.ticker" path="ticker"/>
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="authenticated.investmentRound.form.label.creation" path="creation" readonly="true"/>
	</jstl:if>
	<acme:form-textbox code="authenticated.investmentRound.form.label.kindRound" path="kindRound"/>
	<acme:form-textbox code="authenticated.investmentRound.form.label.title" path="title"/>
	<acme:form-textbox code="authenticated.investmentRound.form.label.description" path="description"/>
	<acme:form-money code="authenticated.investmentRound.form.label.amount" path="amount"/>
	<acme:form-url code="authenticated.investmentRound.form.label.link" path="link"/>
	<acme:form-checkbox code="authenticated.investmentRound.form.label.active" path="active"/>
	<acme:form-return code="authenticated.investmentRound.form.button.return"/>
	<acme:form-submit code="authenticated.investmentRoundform.label.activity" action="/entrepreneur/activity/list?id=${id}" method="get"/>
	
	<acme:form-submit test="${command == 'show'}" code="entrepreneur.investmentRound.form.button.update" action="/entrepreneur/investment-round/update"/>
	<acme:form-submit test="${command == 'show'}" code="entrepreneur.investmentRound.form.button.delete" action="/entrepreneur/investment-round/delete"/>
	<acme:form-submit test="${command == 'create'}" code="entrepreneur.investmentRound.form.button.create" action="/entrepreneur/investment-round/create"/>
	<acme:form-submit test="${command == 'update'}" code="entrepreneur.investmentRound.form.button.update" action="/entrepreneur/investment-round/update"/>
	<acme:form-submit test="${command == 'delete'}" code="entrepreneur.investmentRound.form.button.delete" action="/entrepreneur/investment-round/delete"/>

		<acme:form-submit code="entrepreneur.activity.form.label.create.activity" action="/entrepreneur/activity/create?id=${id}" method="get" />
		
</acme:form>

