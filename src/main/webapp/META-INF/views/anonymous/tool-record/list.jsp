<%@page  language ="java"%>


<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:list>
	<acme:list-column code="anonymous.technologyTool.list.label.title" path="title" width="25%"/>
	<acme:list-column code="anonymous.technologyTool.list.label.activitySector" path="activitySector" width="20%"/>
	<acme:list-column code="anonymous.technologyTool.list.label.email" path="email" width="35%"/>
	<acme:list-column code="anonymous.technologyTool.list.label.stars" path="stars" width="20%"/>
</acme:list>


