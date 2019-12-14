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

	<acme:form-hidden path="descriptorId"/>
	<acme:form-textbox code="employer.job.form.label.reference" path="reference" />
	<acme:check-access test="${command != 'create'}">
		<acme:form-textbox readonly="true" code="employer.application.form.label.oldstatus" path="oldstatus"/>
		<acme:check-access test="${!finalMode}">
			<acme:form-select code="employer.application.form.label.status" path="status">
				<acme:form-option code="employer.application.form.label.draft" value="DRAFT"/>
				<acme:form-option code="employer.application.form.label.published" value="PUBLISHED"/>
			</acme:form-select>
		</acme:check-access>
	</acme:check-access>
	<acme:form-textbox code="employer.job.form.label.title" path="title" />
	<acme:form-moment code="employer.job.form.label.deadline" path="deadline" />
	<acme:form-money code="employer.job.form.label.salary" path="salary" />
	<acme:form-url code="employer.job.form.label.moreInfo" path="moreInfo" />
	<acme:form-textarea code="employer.job.form.label.description" path="description" />
	
	<!-- Descriptor -->
	<acme:message code="employer.job.descriptor" />
	<acme:form-textarea code="employer.job.form.label.descriptor" path="descriptor-description" />


	<acme:check-access test="${!finalMode}">
		<acme:form-submit test="${command == 'show'}" code="employer.job.form.button.update" action="/employer/job/update" />
		<acme:form-submit test="${command == 'update'}" code="employer.job.form.button.update" action="/employer/job/update" />
	</acme:check-access>

	<acme:form-submit test="${command == 'create'}" code="employer.job.form.button.create" action="/employer/job/create" />

	<!-- Duties -->
	<acme:check-access test="${duties != 0 && command != 'create'}">
		<acme:form-submit test="${command != 'create'}" method="get" code="employer.job.form.button.duties"
		action="/employer/duty/list?id=${descriptorId}&ref=${reference}" />
	</acme:check-access>
	
	<acme:check-access test="${duties == 0 || !finalMode && command != 'create'}">
		<acme:form-submit test="${command != 'create'}" method="get" code="employer.job.form.button.create-duty"
		action="/employer/duty/create?id=${descriptorId}" />
	</acme:check-access>
	
	<!-- Delete -->
	<acme:form-submit test="${command == 'show' || command == 'update'}" code = "employer.job.form.button.delete" action="/employer/job/delete"/>
	<acme:form-submit test="${command == 'delete'}" code="employer.job.form.button.delete" action="/employer/job/delete"/>

	<!-- Audit Records -->
	<acme:form-submit test="${command != 'create'}" method="get" code="employer.job.form.button.auditRecord"
		action="/employer/audit-record/list?id=${id}&ref=${reference}" />
		
	<!-- Return -->
	<acme:form-return code="employer.job.form.button.return" />
</acme:form>
