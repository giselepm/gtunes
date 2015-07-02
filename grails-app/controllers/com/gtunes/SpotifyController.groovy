package com.gtunes

import grails.converters.JSON
import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils
import org.codehaus.groovy.grails.web.json.JSONObject

class SpotifyController {

    def validTypes = ['artist', 'album', 'playlist', 'track']
    def token = ''

    def index() {
        render(/Choose one of the methods below to call:
                SearchArtist (criteria)


                /)
    }

    def search() {
        if (params.criteria && params.type && (params.type in validTypes)){

            def parsedCriteria = params.criteria.replace(' ','%20')

            String url = "https://api.spotify.com/v1/search?q=${parsedCriteria}&type=$params.type"

            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);

            HttpResponse response = client.execute(request);

            HttpEntity responseEntity = response.getEntity()
            String jsonAsString = EntityUtils.toString(responseEntity)
            def json = JSON.parse(jsonAsString)

//            json.artists.items.eachWithIndex { item, count ->
//                println "id = ${item.id}, count = ${count}"
//                return
//            }
//
            json.artists.items.each { item ->
                println "id = ${item.id}, genres = ${item.genres}, name = ${item.name}, href = ${item.href}"
            }
//
//            for(JSONObject item: json.artists.items){
//                println "id = ${item.id}, count = ${count}"
////            break
//            }

            render json

        }
        else{
            render 'The request must have a criteria and a type parameters. The valid types are artist, album, playlist and track'
        }


    }

    def String authorize(){

        if (!token) {
            String clientId = 'client_id=218b6810d6f545c89f901d0408de1267'
            String responseType = '&response_type=code'
            def redirectUri = '&redirect_uri=http%3A%2F%2Flocalhost:8080%2FgTunes%2Fspotify'
            def scope = '' //'&scope=user-read-private%20user-read-email'
            def state = '' //'&state=34fFs29kd09'

            String url = "https://accounts.spotify.com/authorize/?${clientId}${responseType}${redirectUri}${scope}"

            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);

            HttpResponse response = client.execute(request);

            token = client.cookieStore.cookies[0].value

//            HttpEntity responseEntity = response.getEntity()
//            String jsonAsString = EntityUtils.toString(responseEntity)
//            def json = JSON.parse(jsonAsString)
//            render json

        }
        render token

    }
}
