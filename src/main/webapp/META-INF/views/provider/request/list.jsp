<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
    <acme:list-column code="provider.request.list.label.title" path="title" width="20%"/>
    <acme:list-column code="provider.request.list.label.creationMoment" path="creationMoment" width="40%"/>       
	<acme:list-column code="provider.request.list.label.deadLine" path="deadLine" width="40%"/> 
	<acme:list-column code="provider.request.list.label.text" path="text" width="40%"/> 
	<acme:list-column code="provider.request.list.label.reward" path="reward" width="40%"/>
	<acme:list-column code="provider.request.list.label.ticker" path="ticker" width="40%"/>      
</acme:list>