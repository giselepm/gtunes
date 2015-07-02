
<%@ page import="com.gtunes.Artist" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'artist.label', default: 'Artist')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-artist" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                <li><g:link class="search" action="search"><g:message code="default.searchSpotify.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-artist" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="idSpotify" title="${message(code: 'artist.idSpotify.label', default: 'Spotify Id')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'artist.name.label', default: 'Name')}" />

                        <td>Action</td>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${artistInstanceList}" status="i" var="artistInstance">

                    <g:form url="[resource:artistInstance, action:'save']" >
                        <g:field name="name" type="hidden" value="${artistInstance.name}"/>
                        <g:field name="idSpotify" type="hidden" value="${artistInstance.idSpotify}"/>

                            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                                <td>${fieldValue(bean: artistInstance, field: "name")}</td>

                                <td>${fieldValue(bean: artistInstance, field: "idSpotify")}</td>

                                <td><g:submitButton name="addgtunes" class="save" value="${message(code: 'default.button.addgtunes.label', default: 'Add to gTunes')}" />


                            </tr>

                    </g:form>


				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${artistInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
