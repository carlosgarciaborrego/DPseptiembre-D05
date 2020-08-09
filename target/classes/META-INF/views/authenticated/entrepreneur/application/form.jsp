<%@page language="java" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form >
	<acme:form-textbox code="authenticated.application.form.label.ticker" path="ticker" readonly="true"/>
	<acme:form-moment code="authenticated.application.form.label.creation" path="creation" readonly="true"/>
	<jstl:if test="${status == 'Pending' || (status == 'Rejected' && answer == '')}">
			<acme:form-select code="authenticated.application.form.label.status" path="status">
			<jstl:choose>
					<jstl:when test="${status == 'Pending' }">
						<jstl:set var="pendingSelected" value="true" />
					</jstl:when>
					<jstl:otherwise>
						<jstl:set var="pendingSelected" value="false" />
					</jstl:otherwise>
				</jstl:choose>
				<jstl:choose>
					<jstl:when test="${status == 'Accepted' }">
						<jstl:set var="acceptedSelected" value="true" />
					</jstl:when>
					<jstl:otherwise>
						<jstl:set var="acceptedSelected" value="false" />
					</jstl:otherwise>
				</jstl:choose>
				<jstl:choose>
					<jstl:when test="${status == 'Rejected' }">
						<jstl:set var="rejectedSelected" value="true" />
					</jstl:when>
					<jstl:otherwise>
						<jstl:set var="rejectedSelected" value="false" />
					</jstl:otherwise>
				</jstl:choose>
				<acme:form-option code="Pending" value="Pending" selected="${pendingSelected}"/>
				<acme:form-option code="Accepted" value="Accepted" selected="${acceptedSelected}"/>
				<acme:form-option code="Rejected" value="Rejected" selected="${rejectedSelected}"/>
			</acme:form-select> 
			<acme:form-textbox code="authenticated.application.form.label.answer" path="answer"/>
	</jstl:if>
	<jstl:if test="${status == 'Accepted' || (status == 'Rejected' && answer != '')}">
			<acme:form-textbox code="authenticated.application.form.label.status" path="status" readonly="true"/>
			<acme:form-textbox code="authenticated.application.form.label.answer" path="answer" readonly="true"/>
	</jstl:if>
	
	<acme:form-textbox code="authenticated.application.form.label.statement" path="statement" readonly="true"/>
	<acme:form-money code="authenticated.application.form.label.offer" path="offer" readonly="true"/>
	<acme:form-return code="authenticated.application.form.button.return"/>
	<jstl:if test="${status == 'Pending' || (status == 'Rejected' && answer == '')}">
		<acme:form-submit test="${command == 'show'}" code="authenticated.entrepreneur.form.button.update" action="/entrepreneur/application/update"/>
		<acme:form-submit test="${command == 'update'}" code="authenticated.entrepreneur.form.button.update" action="/entrepreneur/application/update"/>
	</jstl:if>
	
</acme:form>

