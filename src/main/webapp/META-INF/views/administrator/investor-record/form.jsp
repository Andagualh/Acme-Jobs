<%@ page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.investor-record.form.label.invname" path="name"/>
	<acme:form-textbox code="administrator.investor-record.form.label.invsector" path="sector"/>
	<acme:form-textbox code="administrator.investor-record.form.label.investingstatement" path="statement"/>
	<acme:form-textbox code="administrator.investor-record.form.label.starsnumber" path="stars"/>
	
	<acme:form-submit test="${command == 'show'}"
	code="administrator.investor-record.form.button.update"
	action="/administrator/investor-record/update"/>
	<acme:form-submit test="${command == 'show'}"
	code="administrator.investor-record.form.button.delete"
	action="/administrator/investor-record/delete"/>
	<acme:form-submit test="${command == 'create'}"
	code="administrator.investor-record.form.button.create"
	action="/administrator/investor-record/create"/>
	<acme:form-submit test="${command == 'update'}"
	code="administrator.investor-record.form.button.update"
	action="/administrator/investor-record/update"/>
	<acme:form-submit test="${command == 'delete'}"
	code="administrator.investor-record.form.button.delete"
	action="/administrator/investor-record/delete"/>
	<acme:form-return 
	code="administrator.investor-record.form.button.return"/>
</acme:form>