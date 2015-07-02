<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'artist.label', default: 'Artist')}" />
		<title><g:message code="default.search.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#search-artist" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="search-artist" class="content scaffold-create" role="main">
			<h1><g:message code="default.search.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${artistInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${artistInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form url="[resource:artistInstance, action:'searchSpotify']" >
				<fieldset class="form">
                    <div class="fieldcontain ${hasErrors(bean: artistInstance, field: 'name', 'error')} required">
                        <label for="name">
                            <g:message code="artist.name.label" default="Name" />
                            <span class="required-indicator">*</span>
                        </label>
                        <g:textField name="name" required="" value="${artistInstance?.name}"/>

                    </div>

                </fieldset>
				<fieldset class="buttons">
					<g:submitButton name="searchSpotify" class="searchSpotify" value="${message(code: 'default.button.search.label', default: 'Search')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
