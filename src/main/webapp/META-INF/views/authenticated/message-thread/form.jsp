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
	<acme:form-textbox code="authenticated.message-thread.form.label.title" path="title" readonly="true"/>
	
	<acme:check-access test="${command != 'create'}">
		<acme:form-moment code="authenticated.message-thread.form.label.creationMoment" path="creationMoment" readonly="true"/>
		<acme:form-submit method="get" code="authenticated.message-thread.list.button.messages" action="/authenticated/message/list?id=${id}"/>
		<acme:check-access test="${isAdministrator}">
			<acme:form-submit method="get" code="authenticated.message-thread.list.button.users" action="/authenticated/user-account/list?id=${id}"/>
		</acme:check-access>
	</acme:check-access>
	
	<acme:menu-separator />
	
	<acme:check-access test="${isAdministrator}">
		<acme:message code="authenticated.message-thread.add"/>
		<acme:form-textarea code="administrator.message-thread.form.label.user.username" path="usernameAdd" />	
		<acme:form-textarea code="administrator.message-thread.form.label.user.email" path="emailAdd" />	
		
		<acme:message code="authenticated.message-thread.delete"/>
		<acme:form-textarea code="administrator.message-thread.form.label.user.username" path="usernameDelete" />	
		<acme:form-textarea code="administrator.message-thread.form.label.user.email" path="emailDelete" />	
	</acme:check-access>
	
	<acme:form-submit test="${command == 'create'}"
	code="authenticated.message-thread.form.button.create" 
	action="/authenticated/message-thread/create"/>
	
	<acme:check-access test="${isAdministrator}">
		<acme:form-submit test="${command == 'show'}"
		code="authenticated.user-account.form.button.update" 
		action="/authenticated/message-thread/update"/>
		
		<acme:form-submit test="${command == 'update'}"
		code="authenticated.user-account.form.button.update" 
		action="/authenticated/message-thread/update"/>
	</acme:check-access>
	
	<acme:form-return code="authenticated.message-thread.form.button.return" />
</acme:form>
