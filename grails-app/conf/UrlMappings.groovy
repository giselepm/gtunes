class UrlMappings {

	static mappings = {

//        "/showSong/$id" {
//            controller = 'song'
//            action = 'show'
//
//
//        }
//
//        "/showArtist/$id" (controller:'artist', action:'show')
//
//        "/artist/show/$id(.$format)"{
//            controller = 'artist'
//            action = 'edit'
////            format = 'gsp'
//
//            constraints {
//               format matches: /json/
//
//            }
//
//        }
//

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/playlists"(resources: "playlist")

//        "/"(controller:"/store")
        "/"(view:"/index")
        "500"(view:'/error')
	}
}
