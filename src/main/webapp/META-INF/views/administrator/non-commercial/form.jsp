<%@ page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.non-commercial.form.label.banner" path="banner"/>
	<acme:form-textbox code="administrator.non-commercial.form.label.slogan" path="slogan"/>
	<acme:form-textbox code="administrator.non-commercial.form.label.url" path="url"/>
	<acme:form-textbox code="administrator.non-commercial.form.label.jingle" path="jingle"/>
	
		<acme:form-submit test="${command == 'show'}"
	code="administrator.non-commercial.form.button.update"
	action="/administrator/non-commercial/update"/>
	<acme:form-submit test="${command == 'show'}"
	code="administrator.non-commercial.form.button.delete"
	action="/administrator/non-commercial/delete"/>
	<acme:form-submit test="${command == 'create'}"
	code="administrator.non-commercial.form.button.create"
	action="/administrator/non-commercial/create"/>
	<acme:form-submit test="${command == 'update'}"
	code="administrator.non-commercial.form.button.update"
	action="/administrator/non-commercial/update"/>
	<acme:form-submit test="${command == 'delete'}"
	code="administrator.non-commercial.form.button.delete"
	action="/administrator/non-commercial/delete"/>
	<acme:form-return 
	code="administrator.non-commercial.form.button.return"/>
</acme:form>