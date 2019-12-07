<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="anonymous.announcements.form.label.title" path="title"/>
	<acme:form-moment code="anonymous.announcements.form.label.creationdate" path="creationdate"/>
	<acme:form-url code="anonymous.announcements.form.label.addinfo" path="addinfo"/>
	<acme:form-textarea code="anonymous.announcements.form.label.text" path="text"/>
	
	<acme:form-return code="anonymous.announcements.form.button.return"/>
</acme:form>