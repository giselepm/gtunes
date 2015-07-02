<%@ page import="com.gtunes.Album" %>



<div class="fieldcontain ${hasErrors(bean: albumInstance, field: 'artist', 'error')} required">
	<label for="artist">
		<g:message code="album.artist.label" default="Artist" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="artist" name="artist.id" from="${com.gtunes.Artist.list()}" optionKey="id" required="" value="${albumInstance?.artist?.name}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: albumInstance, field: 'genre', 'error')} required">
	<label for="genre">
		<g:message code="album.genre.label" default="Genre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="genre" required="" value="${albumInstance?.genre}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: albumInstance, field: 'songs', 'error')} ">
	<label for="songs">
		<g:message code="album.songs.label" default="Songs" />
		
	</label>
	<g:select name="songs" from="${com.gtunes.Song.list()}" multiple="multiple" optionKey="id" size="5" value="${albumInstance?.songs*.title}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: albumInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="album.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${albumInstance?.title}"/>

</div>

