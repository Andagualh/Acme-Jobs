<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.announcements.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.announcements.form.label.creationdate" path="creationdate"/>
	<acme:form-url code="authenticated.announcements.form.label.addinfo" path="addinfo"/>
	<acme:form-textarea code="authenticated.announcements.form.label.text" path="text"/>
	
	<acme:form-return code="authenticated.announcements.form.button.return"/>
</acme:form>