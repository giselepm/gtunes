class BootStrap {

    def init = { servletContext ->
        new SpotifyHttpClient()
    }
    def destroy = {
    }
}
