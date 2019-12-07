<%@ page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="anonymous.investor-record.form.label.invname" path="name"/>
	<acme:form-textbox code="anonymous.investor-record.form.label.invsector" path="sector"/>
	<acme:form-textbox code="anonymous.investor-record.form.label.investingstatement" path="statement"/>
	<acme:form-textbox code="anonymous.investor-record.form.label.starsnumber" path="stars"/>
	
	<acme:form-return code="anonymous.investor-record.form.button.return"/>
</acme:form>