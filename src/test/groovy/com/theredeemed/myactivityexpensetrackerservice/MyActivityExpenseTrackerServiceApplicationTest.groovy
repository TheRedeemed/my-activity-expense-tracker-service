package com.theredeemed.myactivityexpensetrackerservice

import com.theredeemed.myactivityexpensetrackerservice.MyActivityExpenseTrackerServiceApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import spock.lang.Specification

@SpringBootTest(classes = MyActivityExpenseTrackerServiceApplication.class)
//This annotation starts the ApplicationContext which is needed for integration test
class MyActivityExpenseTrackerServiceApplicationTest extends Specification {

    @Autowired
    ApplicationContext applicationContext

    def "Context is as expected"() {
        expect:
        applicationContext
    }
}
