<%@ page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="sponsor.commercial.form.label.banner" path="banner" />
	<acme:form-textbox code="sponsor.commercial.form.label.slogan" path="slogan" />
	<acme:form-textbox code="sponsor.commercial.form.label.url" path="url" />
	
	<acme:check-access test="${command == 'create'}">
		<acme:message code="sponsor.commercial.form.credit-card" />
		<acme:form-textbox code="sponsor.commercial.form.label.ownerName" path="ownerName"/>
		<acme:form-textbox code="sponsor.commercial.form.label.number" path="number"/>
		<acme:form-textbox code="sponsor.commercial.form.label.deadline" path="deadline"/>
		<acme:form-textbox code="sponsor.commercial.form.label.cvv" path="cvv"/>
	</acme:check-access>
	
	<acme:form-submit test="${command != 'create' && cardId != null}"
	method="get"
	code="sponsor.commercial.form.button.card"
	action="/sponsor/credit-card/show?id=${cardId}" />
	
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

