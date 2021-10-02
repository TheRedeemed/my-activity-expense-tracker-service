package com.theredeemed.myactivityexpensetrackerservice

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import spock.lang.Specification

@SpringBootTest     //This annotation starts the ApplicationContext which is needed for integration test
class MyActivityExpenseTrackerServiceApplicationTest extends Specification {

    @Autowired
    ApplicationContext applicationContext

    def "Context is as expected"() {
        expect: applicationContext
    }
}
