<%@page  language ="java"%>


<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:list>
	<acme:list-column code="authenticated.entrepreneur.list.label.startUpName" path="startUpName" width="25%"/>
	<acme:list-column code="authenticated.entrepreneur.list.label.activitySector" path="activitySector" width="25%"/>
	<acme:list-column code="authenticated.entrepreneur.list.label.qualificationRecord" path="qualificationRecord" width="25%"/>
	<acme:list-column code="authenticated.entrepreneur.list.label.skillRecord" path="skillRecord" width="25%"/>
</acme:list>


