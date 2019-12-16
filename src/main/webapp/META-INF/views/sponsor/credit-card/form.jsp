<%@ page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="false">
	<acme:form-textbox code="sponsor.credit-card.form.label.ownerName" path="ownerName"/>
	<acme:form-textbox code="sponsor.credit-card.form.label.number" path="number"/>
	<acme:form-textbox code="sponsor.credit-card.form.label.deadline" path="deadline"/>
	<acme:form-textbox code="sponsor.credit-card.form.label.cvv" path="cvv"/>
	
	<acme:form-submit test="${command == 'create'}"
	code="sponsor.credit-card.form.button.create"
	action="/sponsor/credit-card/create"/>
	
	<acme:form-submit test="${command == 'update'}"
	code="sponsor.credit-card.form.button.update"
	action="/sponsor/credit-card/update"/>
	
	<acme:form-submit test="${command == 'show'}"
	code="sponsor.credit-card.form.button.update"
	action="/sponsor/credit-card/update"/>
	
	<acme:form-return code="sponsor.credit-card.form.button.return"/>
</acme:form>