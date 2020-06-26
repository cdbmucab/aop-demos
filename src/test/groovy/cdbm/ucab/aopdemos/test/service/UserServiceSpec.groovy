package cdbm.ucab.aopdemos.test.service

import cdbm.ucab.aopdemos.data.UserData
import cdbm.ucab.aopdemos.repository.UserDataMapper
import cdbm.ucab.aopdemos.service.UserService
import spock.lang.Specification

class UserServiceSpec extends Specification {

    UserService userService = new UserService()

    def "test pushUserById"() {
        setup:
        userService.userDataMapper = Mock(UserDataMapper) {
            1 * getById(_) >> userData
        }

        when:
        UserData result = userService.pushUserById(System.currentTimeMillis())

        then:
        result.email == expectedEmail

        where:
        userData                                || expectedEmail
        new UserData(email: "some@gmail.com")   || "some@gmail.com"
    }
}
