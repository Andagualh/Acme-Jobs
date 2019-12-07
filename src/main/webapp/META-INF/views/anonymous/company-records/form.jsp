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

<acme:form readonly="true">
	<acme:form-textbox code="anonymous.company-records.form.label.name" path="name"/>
	<acme:form-textbox code="anonymous.company-records.form.label.sector" path="sector"/>
	<acme:form-textbox code="anonymous.company-records.form.label.CEOname" path="CEOname"/>
	<acme:form-textarea code="anonymous.company-records.form.label.description" path="description"/>
	<acme:form-url code="anonymous.company-records.form.label.web" path="web"/>
	<acme:form-textbox code="anonymous.company-records.form.label.phone" path="phone"/>
	<acme:form-textbox code="anonymous.company-records.form.label.email" path="email"/>
	<acme:form-checkbox code="anonymous.company-records.form.label.incorporated" path="incorporated"/>
	<acme:form-integer code="anonymous.company-records.form.label.stars" path="stars"/>
	
	<acme:form-return code="anonymous.company-records.form.button.return"/>
</acme:form>
