package com.gtunes

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(UserController)
@Mock(User)
class UserControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void testPasswordsDoNotMatch() {
        when:
            request.method = 'POST'
            params.login = 'henry'
            params.password = 'password'
            params.confirm = 'wrongPassword'
            params.firstName = 'Henry'
            params.lastName = 'Rollins'
            def model = controller.register()
            def user = model.user

        then:
            user.hasErrors()
            'user.password.dontmatch' == user.errors['password'].code
    }

    void testRegistrationFailed() {
        when:
            request.method = 'POST'
            params.login = ''
            def model = controller.register()
            def user = model.user

        then:
            user.hasErrors()
            session.user == null
            'blank' == user.errors['login'].code
            'nullable' == user.errors['firstName'].code
            'nullable' == user.errors['lastName'].code
    }

    void testRegistrationSuccess() {
        when:
            request.method = 'POST'
            params.login = 'henry'
            params.password = 'password'
            params.confirm = 'password'
            params.firstName = 'Henry'
            params.lastName = 'Rollins'
            controller.register()

        then:
            '/store' == response.redirectedUrl
            session.user != null
    }

    void testLoginUserNotFound() {
        when:
            request.method = 'POST'
            params.login = 'frank'
            params.password = 'hotrats'
            controller.login()
            def cmd = model.loginCmd

        then:
            cmd.hasErrors()
            'user.not.found' == cmd.errors['login'].code
            session.user == null
            '/store/index' == view
    }

    void testLoginFailurePasswordInvalid() {
        when:
            request.method = 'POST'
            def u = new User(login: 'maynard',
                    firstName: 'Maynard',
                    lastName: 'Keenan',
                    password: 'undertow').save()

            params.login = 'maynard'
            params.password = 'lateralus'
            controller.login()
            def cmd = model.loginCmd

        then:
            u != null

            cmd.hasErrors()
            'user.password.invalid' == cmd.errors['password'].code
            session.user == null
            '/store/index' == view
    }

    void testLoginSuccess() {
        when:
            request.method = 'POST'
            def u = new User(login: 'maynard',
                    firstName: 'Maynard',
                    lastName: 'Keenan',
                    password: 'undertow').save()
            params.login = 'maynard'
            params.password = 'undertow'
            controller.login()

        then:
            u != null

            session.user != null
            '/store' == response.redirectedUrl
    }

}
