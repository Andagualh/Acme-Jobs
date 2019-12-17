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
	<acme:form-textbox code="auditor.job.form.label.reference" path="reference" placeholder="EEEE-JJJJ"/>
	<acme:form-textbox code="auditor.job.form.label.status" path="status" />
	<acme:form-textbox code="auditor.job.form.label.title" path="title" />
	<acme:form-moment code="auditor.job.form.label.deadline" path="deadline" />
	<acme:form-money code="auditor.job.form.label.salary" path="salary" />
	<acme:form-url code="auditor.job.form.label.moreInfo" path="moreInfo" />
	<acme:form-textarea code="auditor.job.form.label.description" path="description" />

	<acme:message code="auditor.job.descriptor" />
	<acme:form-textarea readonly="true" code="auditor.job.form.label.descriptor" path="descriptor" />

	<acme:form-submit method="get" code="auditor.job.form.button.duties"
		action="/auditor/duty/list?id=${descriptorId}&ref=${reference}" />
	<acme:form-submit method="get" code="auditor.job.form.button.auditRecord"
		action="/auditor/audit-record/list?id=${id}&ref=${reference}" />
		
	<acme:form-submit test="${!isAudited}" method="get" code="auditor.job.form.button.audit-records.apply"
		action="/auditor/audit-record/create?id=${id}&ref=${reference}" />



	<acme:form-return code="auditor.job.form.button.return" />
</acme:form>
