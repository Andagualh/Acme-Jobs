<%@ page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.commercial.form.label.banner" path="banner"/>
	<acme:form-textbox code="administrator.commercial.form.label.slogan" path="slogan"/>
	<acme:form-textbox code="administrator.commercial.form.label.url" path="url"/>
	<acme:form-textbox code="administrator.commercial.form.label.card" path="card"/>
	
	<acme:form-submit test="${command == 'show'}"
	code="administrator.commercial.form.button.update"
	action="/administrator/commercial/update"/>
	<acme:form-submit test="${command == 'show'}"
	code="administrator.commercial.form.button.delete"
	action="/administrator/commercial/delete"/>
	<acme:form-submit test="${command == 'create'}"
	code="administrator.commercial.form.button.create"
	action="/administrator/commercial/create"/>
	<acme:form-submit test="${command == 'update'}"
	code="administrator.commercial.form.button.update"
	action="/administrator/commercial/update"/>
	<acme:form-submit test="${command == 'delete'}"
	code="administrator.commercial.form.button.delete"
	action="/administrator/commercial/delete"/>
	<acme:form-return 
	code="administrator.commercial.form.button.return"/>
</acme:form>