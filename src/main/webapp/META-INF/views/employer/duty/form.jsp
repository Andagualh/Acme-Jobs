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
	<acme:form-textbox code="employer.duty.form.label.title" path="title" />
	<acme:form-textbox code="employer.duty.form.label.description" path="description" />
	<acme:check-access test="${command != 'create'}">
		<acme:form-textbox code="employer.duty.form.label.percent" path="percent"/>
	</acme:check-access>
	<acme:check-access test="${command == 'create'}">
		<acme:form-textbox code="employer.duty.form.label.daysToComplete" path="daysToComplete"/>
	</acme:check-access>
	
	<acme:form-submit test="${command == 'create'}" code="employer.duty.form.button.create" action="/employer/duty/create?id=${id}" />

	<acme:form-return code="employer.duty.form.button.return" />
	
	
</acme:form>
