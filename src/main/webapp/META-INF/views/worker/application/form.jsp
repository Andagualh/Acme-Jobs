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
	<acme:form-textbox code="worker.application.form.label.reference" path="ref" />
	<jstl:if test="${command != 'create'}">
	<acme:form-moment code="worker.application.form.label.creationmoment" path="creationMoment" />
	<acme:form-textbox code="worker.application.form.label.status" path="status"/>
	<jstl:if test="${status != 'PENDING' }">
	<acme:form-textbox code="worker.application.form.label.justification" path="justification"/>
	</jstl:if>
	</jstl:if>
	<acme:form-textbox code="worker.application.form.label.statement" path="statement"/>
	<acme:form-textbox code="worker.application.form.label.skill" path="skill"/>
	<acme:form-textbox code="worker.application.form.label.qualification" path="qualification"/>
	
	<acme:form-submit test="${command == 'create'}"
	code="worker.application.form.button.create"
	action="/worker/application/create"/>

	<acme:form-return code="worker.application.form.button.return" />
</acme:form>
