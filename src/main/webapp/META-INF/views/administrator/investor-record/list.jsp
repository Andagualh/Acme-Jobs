<%@ page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.investor-record.list.label.invname" path="name" width="20%"/>
	<acme:list-column code="administrator.investor-record.list.label.invsector" path="sector" width="20%"/>
	<acme:list-column code="administrator.investor-record.list.label.investingstatement" path="statement" width="20%"/>
	<acme:list-column code="administrator.investor-record.list.label.starsnumber" path="stars" width="20%"/>
</acme:list>