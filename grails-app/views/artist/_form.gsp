<%@ page import="com.gtunes.Artist" %>



<div class="fieldcontain ${hasErrors(bean: artistInstance, field: 'name', 'error')} required">
    <label for="name">
        <g:message code="artist.name.label" default="Name" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="name" required="" value="${artistInstance?.name}"/>

</div>
<div class="fieldcontain ${hasErrors(bean: artistInstance, field: 'albums', 'error')} ">
	<label for="albums">
		<g:message code="artist.albums.label" default="Albums" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${artistInstance?.albums?}" var="a">
    <li><g:link controller="album" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="album" action="create" params="['artist.id': artistInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'album.label', default: 'Album')])}</g:link>
</li>
</ul>


</div>


