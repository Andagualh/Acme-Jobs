<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="provider.request.form.label.title" path="title"/>
	<jstl:if test="${command != 'create'}">
	<acme:form-moment 
		code="provider.request.form.label.creationMoment" 
		path="creationMoment"
		readonly="true"/>
	</jstl:if>
	<acme:form-moment code="provider.request.form.label.deadLine" path="deadLine"/>
	<acme:form-textarea code="provider.request.form.label.text" path="text"/>
	<acme:form-money code="provider.request.form.label.reward" path="reward"/>
	<acme:form-textbox code="provider.request.form.label.ticker" path="ticker"/>

	<jstl:if test="${command == 'create' }">
		<acme:form-checkbox code="provider.request.form.label.isCreated" path="accept"/>
	</jstl:if>
	<acme:form-submit test="${command == 'show' }"
		code="provider.request.form.button.update"
		action="/provider/request/update"/>
	<acme:form-submit test="${command == 'show' }"
		code="provider.request.form.button.delete" 
		action="/provider/request/delete"/>
	<acme:form-submit test="${command == 'create' }"
		code="provider.request.form.button.create" 
		action="/provider/request/create"/>
	<acme:form-submit test="${command == 'update' }"
		code="provider.request.form.button.update" 
		action="/provider/request/update"/>
	<acme:form-submit test="${command == 'delete' }"
		code="provider.request.form.button.delete" 
		action="/provider/request/delete"/>
	<acme:form-return 
		code="provider.request.form.button.return"/>
</acme:form>