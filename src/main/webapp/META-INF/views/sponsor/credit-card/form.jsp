<%@ page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="sponsor.credit-card.form.label.ownerName" path="ownerName"/>
	<acme:form-textbox code="sponsor.credit-card.form.label.number" path="number"/>
	<acme:form-textbox code="sponsor.credit-card.form.label.deadline" path="deadline"/>
	<acme:form-textbox code="sponsor.credit-card.form.label.cvv" path="cvv"/>
	
	<acme:form-return 
	code="sponsor.credit-card.form.button.return"/>
	
</acme:form>