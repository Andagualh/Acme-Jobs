
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
	<acme:list-column code="employer.descriptor.list.label.descriptor" path="description" width="70%" />

</acme:list>

<acme:form>
	<acme:form-submit test="${isEmpty}" method="get" code="employer.descriptor.form.button.create"
			action="/employer/descriptor/create" />
</acme:form>

<acme:form-return code="employer.job.form.button.return" />
