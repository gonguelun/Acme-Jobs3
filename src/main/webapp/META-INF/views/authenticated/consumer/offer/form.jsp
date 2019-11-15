<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.consumer.offer.form.label.title" path="title"/>
	
	<jstl:if test="${command != 'create'}">
	<acme:form-moment code="authenticated.consumer.offer.form.label.moment" path="moment" readonly="true"/>
	</jstl:if>
	
	<acme:form-moment code="authenticated.consumer.offer.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="authenticated.consumer.offer.form.label.text" path="text"/>
	<acme:form-money code="authenticated.consumer.offer.form.label.minPrice" path="minPrice"/>
	<acme:form-money code="authenticated.consumer.offer.form.label.maxPrice" path="maxPrice"/>
	<acme:form-textbox code="authenticated.consumer.offer.form.label.ticker" path="ticker"/>
	<acme:form-checkbox code="authenticated.consumer.offer.form.label.checkbox" path="checkbox"/>

	<acme:form-submit test="${command == 'create'}" code="authenticated.consumer.offer.form.button.create" action="/authenticated/consumer/offer/create"/>
	<acme:form-return code="authenticated.consumer.offer.form.button.return"/>
</acme:form>
