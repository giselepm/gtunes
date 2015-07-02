package com.gtunes

class Artist {
    String name
    Date dateCreated
    String idSpotify

    static hasMany = [albums:Album]

    static constraints = {
        name blank: false, nullable: false
        idSpotify nullable: true
    }
}
