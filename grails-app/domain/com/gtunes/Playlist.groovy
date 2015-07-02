package com.gtunes

//import grails.rest.Resource
//
//@Resource(uri='/playlists')
class Playlist {

    String title

    static hasMany = [songs:Song]

    static constraints = {
        title blank: false
    }
}
