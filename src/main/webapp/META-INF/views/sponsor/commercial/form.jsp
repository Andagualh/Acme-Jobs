<%@ page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="sponsor.commercial.form.label.banner" path="banner" />
	<acme:form-textbox code="sponsor.commercial.form.label.slogan" path="slogan" />
	<acme:form-textbox code="sponsor.commercial.form.label.url" path="url" />
	
	<acme:form-submit test="${command == 'show'}"
	code="sponsor.commercial.form.button.update"
	action="/sponsor/commercial/update"/>
	<acme:form-submit test="${command == 'show'}"
	code="sponsor.commercial.form.button.delete"
	action="/sponsor/commercial/delete"/>
	<acme:form-submit test="${command == 'create'}"
	code="sponsor.commercial.form.button.create"
	action="/sponsor/commercial/create"/>
	<acme:form-submit test="${command == 'update'}"
	code="sponsor.commercial.form.button.update"
	action="/sponsor/commercial/update"/>
	<acme:form-submit test="${command == 'delete'}"
	code="sponsor.commercial.form.button.delete"
	action="/sponsor/commercial/delete"/>

	<acme:form-return code="sponsor.commercial.form.button.return" />
</acme:form>

