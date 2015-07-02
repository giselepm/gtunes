package com.gtunes

class Song {

    String title
    Integer duration
    Date dateCreated

    static belongsTo = [album:Album]

    static constraints = {
        title blank: false
        duration min: 1
    }
}
