<%--
- form.jsp
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

<acme:form readonly="false">
	<acme:form-textbox code="authenticated.message-thread.form.label.title" path="title" />
	
	<acme:check-access test="${command != 'create'}">
		<acme:form-moment code="authenticated.message-thread.form.label.creationMoment" path="creationMoment"/>
		<acme:form-submit method="get" code="authenticated.message-thread.list.button.messages" action="/authenticated/message/list?id=${id}"/>
		<acme:form-submit method="get" code="authenticated.message-thread.list.button.users" action="/authenticated/user-account/list?id1=${id}"/>
	</acme:check-access>
	
	<acme:form-submit test="${command == 'create'}"
	code="authenticated.message-thread.form.button.create" 
	action="/authenticated/message-thread/create"/>
	
	<acme:form-return code="authenticated.message-thread.form.button.return" />
</acme:form>
