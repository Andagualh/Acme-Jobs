
<%--
- list.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.challenge.list.label.title" path="title" width="20%"/>
	<acme:list-column code="authenticated.challenge.list.label.deadline" path="deadline" width="40%"/>
	<acme:list-column code="authenticated.challenge.list.label.description" path="description" width="40%"/>
	<acme:list-column code="authenticated.challenge.list.label.gold" path="gold" width="40%"/>
	<acme:list-column code="authenticated.challenge.list.label.silver" path="silver" width="40%"/>
	<acme:list-column code="authenticated.challenge.list.label.bronze" path="bronze" width="40%"/>	
	<acme:list-column code="authenticated.challenge.list.label.goldreward" path="goldreward" width="40%"/>	
	<acme:list-column code="authenticated.challenge.list.label.silverreward" path="silverreward" width="40%"/>	
	<acme:list-column code="authenticated.challenge.list.label.bronzereward" path="bronzereward" width="40%"/>	
</acme:list>