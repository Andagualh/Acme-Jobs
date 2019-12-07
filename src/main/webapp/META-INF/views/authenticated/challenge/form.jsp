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
	<acme:form-textbox code="authenticated.challenge.form.label.title" path="title"/>
	<acme:form-textbox code="authenticated.challenge.form.label.deadline" path="deadline"/>
	<acme:form-textbox code="authenticated.challenge.form.label.description" path="description"/>
	<acme:form-textbox code="authenticated.challenge.form.label.gold" path="gold"/>
	<acme:form-textbox code="authenticated.challenge.form.label.silver" path="silver"/>
	<acme:form-textbox code="authenticated.challenge.form.label.bronze" path="bronze"/>
	<acme:form-textbox code="authenticated.challenge.form.label.goldreward" path="goldreward"/>	
	<acme:form-textbox code="authenticated.challenge.form.label.silverreward" path="silverreward"/>	
	<acme:form-textbox code="authenticated.challenge.form.label.bronzereward" path="bronzereward"/>	
	
	<acme:form-return code="authenticated.challenge.form.button.return"/>
</acme:form>
