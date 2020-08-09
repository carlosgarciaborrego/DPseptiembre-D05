<%@page language="java" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form readonly="${command != 'create'}">
	<acme:form-textbox code="authenticated.application.form.label.ticker" path="ticker" />
	<jstl:if test="${command != 'create'}">
			<acme:form-moment code="authenticated.application.form.label.creation" path="creation" readonly="true"/>
	</jstl:if>
	<acme:form-textbox code="authenticated.application.form.label.statement" path="statement"/>
	<acme:form-money code="authenticated.application.form.label.offer" path="offer"/>
	<acme:form-return code="authenticated.application.form.button.return"/>

	<acme:form-submit test="${command == 'create'}" code="authenticated.investor.application.form.label.application" action="/investor/application/create"/>
	
</acme:form>

