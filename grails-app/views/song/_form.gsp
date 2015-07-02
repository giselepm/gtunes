<%@ page import="com.gtunes.Song" %>



<div class="fieldcontain ${hasErrors(bean: songInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="song.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${songInstance?.title}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: songInstance, field: 'duration', 'error')} required">
	<label for="duration">
		<g:message code="song.duration.label" default="Duration" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="duration" type="number" min="1" value="${songInstance.duration}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: albumInstance, field: 'album', 'error')} required">
    <label for="album">
        <g:message code="song.album.label" default="Album" />
        <span class="required-indicator">*</span>
    </label>
    <g:select id="album" name="album.id" from="${com.gtunes.Album.list()}" optionKey="id" required="" value="${songInstance?.album?.name}" class="many-to-one"/>

</div>

