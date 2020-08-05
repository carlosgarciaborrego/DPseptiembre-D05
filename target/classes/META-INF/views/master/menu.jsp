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
			<acme:menu-suboption code="master.menu.anonymous.favourite-link" action="http://www.nba.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.listNotice" action="/anonymous/notice/list"/>
			<acme:menu-suboption code="master.menu.anonymous.listTechRec" action="/anonymous/technology-record/list"/>
			<acme:menu-suboption code="master.menu.anonymous.listToolRec" action="/anonymous/tool-record/list"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-suboption code="master.menu.administrator.listNotice" action="/administrator/notice/list"/>
			<acme:menu-suboption code="master.menu.administrator.createNotice" action="/administrator/notice/create"/>
			<acme:menu-suboption code="master.menu.administrator.listInquiry" action="/administrator/inquiry/list"/>
			<acme:menu-suboption code="master.menu.administrator.createInquiry" action="/administrator/inquiry/create"/>
			<acme:menu-suboption code="master.menu.administrator.listOverture" action="/administrator/overture/list"/>
			<acme:menu-suboption code="master.menu.administrator.createOverture" action="/administrator/overture/create"/>
			<acme:menu-suboption code="master.menu.administrator.listTechnologyRecord" action="/administrator/technology-record/list"/>
			<acme:menu-suboption code="master.menu.administrator.createTechnologyRecord" action="/administrator/technology-record/create"/>
			<acme:menu-suboption code="master.menu.administrator.listToolRecord" action="/administrator/tool-record/list"/>
			<acme:menu-suboption code="master.menu.administrator.createToolRecord" action="/administrator/tool-record/create"/>
			<acme:menu-suboption code="master.menu.administrator.listChallenge" action="/administrator/challenge/list"/>
			<acme:menu-suboption code="master.menu.administrator.createChallenge" action="/administrator/challenge/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.authenticated" access="hasRole('Authenticated')">
			<acme:menu-suboption code="master.menu.authenticated.listNotice" action="/authenticated/notice/list"/>
			<acme:menu-suboption code="master.menu.authenticated.listTechRec" action="/authenticated/technology-record/list"/>
			<acme:menu-suboption code="master.menu.authenticated.listToolRec" action="/authenticated/tool-record/list"/>
			<acme:menu-suboption code="master.menu.authenticated.listInquiry" action="/authenticated/inquiry/list"/>
			<acme:menu-suboption code="master.menu.authenticated.listOverture" action="/authenticated/overture/list"/>
			<acme:menu-suboption code="master.menu.authenticated.listChallenge" action="/authenticated/challenge/list"/>
			<acme:menu-suboption code="master.menu.authenticated.listInvestRound" action="/authenticated/investment-round/list"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.provider" access="hasRole('Provider')">
			<acme:menu-suboption code="master.menu.provider.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.consumer" access="hasRole('Consumer')">
			<acme:menu-suboption code="master.menu.consumer.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.entrepreneur" access="hasRole('Entrepreneur')">
			<acme:menu-suboption code="master.menu.entrepreneur.investRound" action="/entrepreneur/investment-round/list"/>
			<acme:menu-suboption code="master.menu.entrepreneur.application" action="/entrepreneur/application/list"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.investor" access="hasRole('Investor')">
			<acme:menu-suboption code="master.menu.investor.application" action="/investor/application/list"/>
		</acme:menu-option>
		
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-provider" action="/authenticated/provider/create" access="!hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.provider" action="/authenticated/provider/update" access="hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.become-consumer" action="/authenticated/consumer/create" access="!hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.consumer" action="/authenticated/consumer/update" access="hasRole('Consumer')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
		
	</acme:menu-right>
</acme:menu-bar>

