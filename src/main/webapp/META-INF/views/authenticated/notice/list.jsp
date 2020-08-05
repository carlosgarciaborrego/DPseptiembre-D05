<%@page  language ="java"%>


<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:list>
	<acme:list-column code="authenticated.notice.list.label.title" path="title" width="25%"/>
	<acme:list-column code="authenticated.notice.list.label.creation" path="creation" width="25%"/>
	<acme:list-column code="authenticated.notice.list.label.deadline" path="deadline" width="25%"/>
	<acme:list-column code="authenticated.notice.list.label.links" path="links" width="25%"/>
</acme:list>


