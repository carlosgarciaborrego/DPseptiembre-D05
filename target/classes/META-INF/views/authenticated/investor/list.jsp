<%@page  language ="java"%>


<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:list>
	<acme:list-column code="authenticated.investor.list.label.name" path="name" width="35%"/>
	<acme:list-column code="authenticated.investor.list.label.activitySector" path="activitySector" width="30%"/>
	<acme:list-column code="authenticated.investor.list.label.profile" path="profile" width="35%"/>
</acme:list>


