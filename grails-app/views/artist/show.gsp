
<%@ page import="com.gtunes.Artist" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'artist.label', default: 'Artist')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-artist" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-artist" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list artist">
			
				<g:if test="${artistInstance?.albums}">
				<li class="fieldcontain">
					<span id="albums-label" class="property-label"><g:message code="artist.albums.label" default="Albums" /></span>
					
						<g:each in="${artistInstance.albums}" var="a">
						<span class="property-value" aria-labelledby="albums-label"><g:link controller="album" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${artistInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="artist.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${artistInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${artistInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="artist.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${artistInstance}" field="name"/></span>
					
				</li>
				</g:if>

				<g:if test="${artistInstance?.idSpotify}">
				<li class="fieldcontain">
					<span id="idSpotify-label" class="property-label"><g:message code="artist.idSpotify.label" default="Spotify Id" /></span>

						<span class="property-value" aria-labelledby="idSpotify-label"><g:fieldValue bean="${artistInstance}" field="idSpotify"/></span>

				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:artistInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${artistInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
