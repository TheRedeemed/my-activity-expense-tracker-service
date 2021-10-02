package com.theredeemed.myactivityexpensetrackerservice.spockpractice

import com.theredeemed.myactivityexpensetrackerservice.model.dto.ActivityDto
import com.theredeemed.myactivityexpensetrackerservice.model.repository.ActivityRepository
import com.theredeemed.myactivityexpensetrackerservice.service.ActivityService
import spock.lang.Specification

class SpockPracticeTest extends Specification {

    def "Addition Test"() {
        given: "Two number a and b"
            int a = 2
            int b = 2
        when: "When adding them get a result"
            int result = a + b
        then: "Expect result to be 4"
            4 == result
    }

    def "Should remove from list"() {
        given:
            //def is a dynamic way to declare a variable without a specific type
            //Groovy gives us a simple way of creating list
            def list = [1, 2, 3, 4]
        when:
            list.remove(0)
        then:
            list == [2, 3, 4]   //the equals() method is not required for validation
    }

    def "Should get an index out of bounds when removing a non-existent item"() {
        given:
            def list = [1, 2, 3, 4]
        when:
            list.remove(20)
        then:
            thrown(IndexOutOfBoundsException)
            list.size() == 4
    }

    //Data driven testing
    def "Numbers to the power of two"() {
        expect:
            Math.pow(a, b) == c

        where:
            a | b | c
            1 | 2 | 1
            2 | 2 | 4
            3 | 2 | 9
    }

    SpockService spockService = Mock(SpockService)

    def "Mocking" () {
        given:
            spockService.gettingList() >> []
        when:
            def list = spockService.gettingList()
        then:
            list == []
    }

    def "Mocking on void" () {
        when:
            spockService.creatingElement(new Object())
        then:
            1 * spockService.creatingElement(_)
    }
}
