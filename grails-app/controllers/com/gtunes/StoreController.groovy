package com.gtunes

import grails.gorm.DetachedCriteria

class StoreController {

    def index() {
//        render 'Welcome to the gTunes store!'
    }

    def shop() {
        def genreList = new DetachedCriteria(Album).distinct('genre').list()

        [top5Albums:  Album.list(max:5, sort:"dateCreated", order:"desc"),
         top5Songs:   Song.list(max:5, sort:"dateCreated", order:"desc"),
         top5Artists: Artist.list(max:5, sort:"dateCreated", order:"desc"),
         genres:  genreList.sort()]
    }
}
