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

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="false">
	<acme:form-textbox code="employer.audit-record.form.label.title" path="title" />
	<acme:form-textbox code="employer.audit-record.form.label.status" path="status" />
	<acme:form-textbox code="employer.audit-record.form.label.creationMoment" path="creationMoment" />
	<acme:form-textbox code="employer.audit-record.form.label.body" path="body" />
		
	<acme:form-return code="employer.job.form.button.return" />
</acme:form>
