package com.theredeemed.myactivityexpensetrackerservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.theredeemed.myactivityexpensetrackerservice.controller.ActivityController
import com.theredeemed.myactivityexpensetrackerservice.model.dto.ActivityDTO
import com.theredeemed.myactivityexpensetrackerservice.service.ActivityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static com.theredeemed.myactivityexpensetrackerservice.constants.ApiConstants.ACTIVITY_ENDPOINT_V1
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print

@SpringBootTest(classes = ActivityController.class)
@AutoConfigureMockMvc
class ActivityControllerTest extends Specification {

    @Autowired
    MockMvc mockMvc

    @Autowired
    ActivityController activityController

    ActivityService activityService = Mock()
    ObjectMapper objectMapper = new ObjectMapper()

    def "Retrieve the list of all activities"() {
        given: 'The list of all activities is requested'
        activityService.getActivityList() >> [getActivityDto()]

        when: 'The retrieveActivityList method is called'
        def response = mockMvc.perform(
                get(ACTIVITY_ENDPOINT_V1)
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andReturn()
                .response

        then: 'Expect the list of all activities to be returned'
        response.status == OK.value()
    }

    def "Create a new activity"() {
        given: 'A new activity needs to be created'
        ActivityDTO dto = ActivityDTO.builder()
                .title("Martial Arts")
                .description("Martial art activity with Mark. Every Monday at 6pm")
                .fee(new BigDecimal("10"))
                .balance(new BigDecimal("0"))
                .build()
        activityService.createNewActivity(_ as ActivityDTO) >> dto

        when: 'The createActivity method is called'
        def response = mockMvc.perform(
                post(ACTIVITY_ENDPOINT_V1)
                        .content(new ObjectMapper().writeValueAsString(dto))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andReturn()
                .response

        then: 'Expect the newly created activity to be returned'
        response.status == CREATED.value()
    }

    def "Update activity balance"() {
        given: 'An expense to need to be added for an activity'
        ActivityDTO dto = ActivityDTO.builder()
                .title("Martial Arts")
                .description("Martial art activity with Mark. Every Monday at 6pm")
                .fee(new BigDecimal("10"))
                .balance(new BigDecimal("10"))
                .build()
        activityService.createNewActivity(_ as ActivityDTO) >> dto

        Map<String, String> updates = new HashMap<>()
        updates.put("title", "Martial Arts")
        updates.put("balance", "10")

        when: 'The expense is added successfully'
        def response = mockMvc.perform(
                patch(ACTIVITY_ENDPOINT_V1)
                        .content(objectMapper.writeValueAsString(updates))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andReturn()
                .response

        then: 'Expect the activity with the added expense to be returned'
        response.status == OK.value()
    }
}
