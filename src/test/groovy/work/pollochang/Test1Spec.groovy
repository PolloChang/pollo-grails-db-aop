package work.pollochang

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class Test1Spec extends Specification implements DomainUnitTest<Test1> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
