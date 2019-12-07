<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.announcements.form.label.title" path="title"/>
	<jstl:if test="${command != 'create'}">
	<acme:form-moment code="administrator.announcements.form.label.creationdate" 
	path="creationdate"
	readonly = "true"/>
	</jstl:if>
	<acme:form-url code="administrator.announcements.form.label.addinfo" path="addinfo"/>
	<acme:form-textarea code="administrator.announcements.form.label.text" path="text"/>
	
	<acme:form-submit test="${command == 'show'}"
	code="administrator.announcements.form.button.update"
	action="/administrator/announcements/update"/>
	<acme:form-submit test="${command == 'show'}"
	code="administrator.announcements.form.button.delete"
	action="/administrator/announcements/delete"/>
	<acme:form-submit test="${command == 'create'}"
	code="administrator.announcements.form.button.create"
	action="/administrator/announcements/create"/>
	<acme:form-submit test="${command == 'update'}"
	code="administrator.announcements.form.button.update"
	action="/administrator/announcements/update"/>
	<acme:form-submit test="${command == 'delete'}"
	code="administrator.announcements.form.button.delete"
	action="/administrator/announcements/delete"/>
	<acme:form-return 
	code="administrator.announcements.form.button.return"/>
</acme:form>

