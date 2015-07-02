package com.gtunes

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.codehaus.groovy.grails.web.mapping.UrlMappings
import spock.lang.Specification


@TestFor(UrlMappings)
@Mock(ArtistController)
class UrlMappingsSpec extends Specification {

    void testShowArtistUrlMapping() {
        when:

        then:

        assertForwardUrlMapping('/showArtist/Jeff_Beck',
                controller: 'artist', action: 'display')

    }

}
