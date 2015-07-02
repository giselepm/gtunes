package com.gtunes

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PlaylistController extends RestfulController {

    static responseFormats = ['json'
                              , 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Playlist.list(params), [status: OK]
    }

    @Transactional
    def save(Playlist playlistInstance) {
        if (playlistInstance == null) {
            render status: NOT_FOUND
            return
        }

        playlistInstance.validate()
        if (playlistInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        playlistInstance.save flush:true
        respond playlistInstance, [status: CREATED]
    }

    @Transactional
    def update(Playlist playlistInstance) {
        if (playlistInstance == null) {
            render status: NOT_FOUND
            return
        }

        playlistInstance.validate()
        if (playlistInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        playlistInstance.save flush:true
        respond playlistInstance, [status: OK]
    }

    @Transactional
    def delete(Playlist playlistInstance) {

        if (playlistInstance == null) {
            render status: NOT_FOUND
            return
        }

        playlistInstance.delete flush:true
        render status: NO_CONTENT
    }
}
