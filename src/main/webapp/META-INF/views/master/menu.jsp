<%--
- menu.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.entities.roles.Provider,acme.entities.roles.Consumer"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.favourite-link" action="http://www.example.com/" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.anonymous.announcement" action="/anonymous/announcements/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.anonymous.company-records.list" action="/anonymous/company-records/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.anonymous.investor-record.list" action="/anonymous/investor-record/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.anonymous.investor-record.topcompanyrecords" action="/anonymous/company-records/toplist" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.anonymous.investor-record.topinvestorrecords" action="/anonymous/investor-record/toplist" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.banners" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.list-non-commercial" action="/administrator/non-commercial/list" />
			<acme:menu-suboption code="master.menu.administrator.create-non-commercial" action="/administrator/non-commercial/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.list-commercial" action="/administrator/commercial/list" />
			<acme:menu-suboption code="master.menu.administrator.create-commercial" action="/administrator/commercial/create" />
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.announcement" action="/administrator/announcements/list" />
			<acme:menu-suboption code="master.menu.administrator.createannoun" action="/administrator/announcements/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.company-records" action="/administrator/company-records/list" />
			<acme:menu-suboption code="master.menu.administrator.company-records.create" action="/administrator/company-records/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.challenge" action="/administrator/challenge/list" />
			<acme:menu-suboption code="master.menu.administrator.challenge.create" action="/administrator/challenge/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.investor-record" action="/administrator/investor-record/list" />
			<acme:menu-suboption code="master.menu.administrator.createinvestorrecord" action="/administrator/investor-record/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.dashboard" action="/administrator/dashboard/show" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.spamlist" action="/administrator/spamlist/list" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.provider" access="hasRole('Provider')">
			<acme:menu-suboption code="master.menu.provider.favourite-link" action="http://www.example.com/"/>
			<acme:menu-suboption code="master.menu.provider.create-request" action="/provider/request/create"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.consumer" access="hasRole('Consumer')">
			<acme:menu-suboption code="master.menu.consumer.favourite-link" action="http://www.example.com/" />
			<acme:menu-suboption code="master.menu.consumer.create-offer" action="/consumer/offer/create" />
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.employer" access="hasRole('Employer')">
			<acme:menu-suboption code="master.menu.employer.favourite-link" action="http://www.example.com/"/>
			<acme:menu-suboption code="master.menu.employer.job.list" action="/employer/job/list_mine"/>
			<acme:menu-suboption code="master.menu.employer.application.list" action="/employer/application/list_mine"/>

			<acme:menu-suboption code="master.menu.employer.application.list-ref" action="/employer/application/list_ref"/>
			<acme:menu-suboption code="master.menu.employer.application.list-status" action="/employer/application/list_status"/>
			<acme:menu-suboption code="master.menu.employer.application.list-creationmoment" action="/employer/application/list_creationmoment"/>

			<acme:menu-suboption code="master.menu.employer.job.create" action="/employer/job/create"/>
			
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.worker" access="hasRole('Worker')">
			<acme:menu-suboption code="master.menu.worker.favourite-link" action="http://www.example.com/"/>
			<acme:menu-suboption code="master.menu.worker.application.list" action="/worker/application/list_mine"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.sponsor" access="hasRole('Sponsor')">
			<acme:menu-suboption code="master.menu.worker.favourite-link" action="http://www.example.com/"/>
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.sponsor.commercial.list" action="/sponsor/commercial/list_mine"/>
			<acme:menu-suboption code="master.menu.sponsor.commercial.create" action="/sponsor/commercial/create"/>
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.sponsor.noncommercial.list" action="/sponsor/non-commercial/list_mine"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.auditor" access="hasRole('Auditor')">
			<acme:menu-suboption code="master.menu.auditor.favourite-link" action="http://www.example.com/"/>
			<acme:menu-suboption code="master.menu.auditor.job.list-rec" action="/auditor/job/list_rec"/>
			<acme:menu-suboption code="master.menu.auditor.job.list-nonrec" action="/auditor/job/list_nonrec"/>
		</acme:menu-option>
	</acme:menu-left>
	

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()" />
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()" />

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update" />
			<acme:menu-suboption code="master.menu.user-account.become-provider" action="/authenticated/provider/create"
				access="!hasRole('Provider')" />
			<acme:menu-suboption code="master.menu.user-account.provider" action="/authenticated/provider/update"
				access="hasRole('Provider')" />
			<acme:menu-suboption code="master.menu.user-account.become-consumer" action="/authenticated/consumer/create"
				access="!hasRole('Consumer')" />
			<acme:menu-suboption code="master.menu.user-account.consumer" action="/authenticated/consumer/update"
				access="hasRole('Consumer')" />
			<acme:menu-suboption code="master.menu.user-account.become-employer" action="/authenticated/employer/create"
				access="!hasRole('Employer')" />
			<acme:menu-suboption code="master.menu.user-account.employer" action="/authenticated/employer/update"
				access="hasRole('Employer')" />
			<acme:menu-suboption code="master.menu.user-account.become-worker" action="/authenticated/worker/create"
				access="!hasRole('Worker')" />
			<acme:menu-suboption code="master.menu.user-account.worker" action="/authenticated/worker/update"
				access="hasRole('Worker')" />	
			<acme:menu-suboption code="master.menu.user-account.become-sponsor" action="/authenticated/sponsor/create"
				access="!hasRole('Sponsor')" />
			<acme:menu-suboption code="master.menu.user-account.sponsor" action="/authenticated/sponsor/update"
				access="hasRole('Sponsor')" />
				
		</acme:menu-option>

		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.announcements" action="/authenticated/announcements/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.company-records.list" action="/authenticated/company-records/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.investor-record.list" action="/authenticated/investor-record/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.challenge.challenges-list" action="/authenticated/challenge/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.offer.offer-list" action="/authenticated/offer/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.request.requests-list" action="/authenticated/request/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.authenticated.job-list" action="/authenticated/job/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.authenticated.message-thread-list" action="/authenticated/message-thread/list" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()" />
	</acme:menu-right>
</acme:menu-bar>

