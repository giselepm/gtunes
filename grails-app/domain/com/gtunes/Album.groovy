package com.gtunes

class Album {
    String title
    String genre
    Date dateCreated

    static hasMany = [songs:Song]
    static belongsTo = [artist:Artist]

    SortedSet songs

    static constraints = {
    }

    static mapping = {
        songs cascade: 'delete'
    }
}
