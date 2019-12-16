
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="auditor.audit-record.list.label.reference" path="ref" width="10%"/>
	<acme:list-column code="auditor.audit-record.list.label.title" path="title" width="10%"/>
	<acme:list-column code="auditor.audit-record.list.label.status" path="status" width="20%"/>
	<acme:list-column code="auditor.audit-record.list.label.creationMoment" path="creationMoment" width="70%"/>
	<acme:list-column code="auditor.audit-record.list.label.body" path="body" width="70%"/>
</acme:list>
<acme:menu-separator />
<acme:form-return code="auditor.audit-record.form.button.return" />