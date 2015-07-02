package com.gtunes

import grails.converters.JSON
import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.NameValuePair
import org.apache.http.client.HttpClient
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.utils.URLEncodedUtils
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.message.BasicNameValuePair
import org.apache.http.params.HttpParams
import org.apache.http.util.EntityUtils

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ArtistController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Artist.list(params), model:[artistInstanceCount: Artist.count()]
    }

    def show(Artist artistInstance) {
        respond artistInstance
    }

    def create() {
        respond new Artist(params)
    }

    @Transactional
    def save(Artist artistInstance) {
        if (artistInstance == null) {
            notFound()
            return
        }

        if (artistInstance.hasErrors()) {
            respond artistInstance.errors, view:'create'
            return
        }

//        String url = $/https://api.spotify.com/v1/search?q=${artistInstance.name.replace(' ','%20')}&type=artist/$
//
//        HttpClient client = HttpClientBuilder.create().build();
//        HttpGet getRequest = new HttpGet(url);
//
//        HttpResponse response = client.execute(getRequest);
//
//        HttpEntity responseEntity = response.getEntity()
//        String jsonAsString = EntityUtils.toString(responseEntity)
//        def json = JSON.parse(jsonAsString)
//       //como eu pego agora o id do primeiro sujeito??
//        artistInstance.idSpotify = json.artists.items[0].id

        artistInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'artist.label', default: 'Artist'), artistInstance.id])
                redirect artistInstance
            }
            '*' { respond artistInstance, [status: CREATED] }
        }
    }

    def edit(Artist artistInstance) {
        respond artistInstance
    }

    @Transactional
    def update(Artist artistInstance) {
        if (artistInstance == null) {
            notFound()
            return
        }

        if (artistInstance.hasErrors()) {
            respond artistInstance.errors, view:'edit'
            return
        }

        artistInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'artist.label', default: 'Artist'), artistInstance.id])
                redirect artistInstance
            }
            '*'{ respond artistInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Artist artistInstance) {

        if (artistInstance == null) {
            notFound()
            return
        }

        artistInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'artist.label', default: 'Artist'), artistInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'artist.label', default: 'Artist'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def search() {
        respond new Artist(params)
    }

    @Transactional
    def searchSpotify(Artist artistInstance) {
        if (artistInstance == null) {
            notFound()
            return
        }

        if (artistInstance.hasErrors()) {
            respond artistInstance.errors, view:'search'
            return
        }

        String url = $/https://api.spotify.com/v1/search?q=${artistInstance.name.replace(' ','%20')}&type=artist/$

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet getRequest = new HttpGet(url);

        HttpResponse response = client.execute(getRequest);

        HttpEntity responseEntity = response.getEntity()
        String jsonAsString = EntityUtils.toString(responseEntity)
        def json = JSON.parse(jsonAsString)
        def artistList = new Artist[json.artists.items.size()]

        json.artists.items.eachWithIndex { item, int count ->
            artistList[count] = new Artist()
            artistList[count].idSpotify = item.id
            artistList[count].name = item.name
        }

        render(view:'searchSpotify', model: [artistInstanceList: artistList])
    }
}
