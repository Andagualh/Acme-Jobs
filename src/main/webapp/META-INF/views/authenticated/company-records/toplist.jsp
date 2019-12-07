<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.company-records.list.label.name" path="name" width="20%"/>
	<acme:list-column code="authenticated.company-records.list.label.description" path="description" width="20%"/>
	
</acme:list>