package com.theredeemed.myactivityexpensetrackerservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.theredeemed.myactivityexpensetrackerservice.model.dto.ActionDTO
import com.theredeemed.myactivityexpensetrackerservice.service.ActionService
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import spock.lang.Specification

import static com.theredeemed.myactivityexpensetrackerservice.constants.ActionTestConstants.*
import static com.theredeemed.myactivityexpensetrackerservice.constants.ApiConstants.ACTION_ENDPOINT_V1

@AutoConfigureMockMvc
@WebMvcTest(ActionController)
@ActiveProfiles("test")
class ActionControllerTest extends Specification {

    @Autowired
    MockMvc mockMvc

    @SpringBean
    ActionService actionService = Mock()

    ObjectMapper objectMapper
    Map<String, String> updateReqPayload

    def setup() {
        objectMapper = new ObjectMapper()
        updateReqPayload = new HashMap<>()
    }

    def "Retrieve the list of all actions"() {
        given: 'The list of all actions is requested'
        actionService.getAllActionList() >> getActionListMock()

        when: 'The getAllActions method is called'
        def response = mockMvc.perform(
                MockMvcRequestBuilders.get(ACTION_ENDPOINT_V1).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .response

        then: 'Expect the response to be successful'
        response.status == HttpStatus.OK.value()
    }

    def "Retrieve an action by ID"() {
        given: 'An action is requested'
        actionService.getActionById(_ as Long) >> getActionDTOMock()

        when: 'The getActionId endpoint is called'
        def response = mockMvc.perform(
                MockMvcRequestBuilders.get(ACTION_ENDPOINT_V1 + "/{id}", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .response

        then: 'Expect the response to be successful'
        response.status == HttpStatus.OK.value()
    }

    def "Create a new action"() {
        given: 'A new action needs to be created'
        ActionDTO dto = getNewActionReqPayload()
        actionService.createNewAction(_ as ActionDTO) >> dto

        when: 'The create endpoint is called'
        def response = mockMvc.perform(
                MockMvcRequestBuilders.post(ACTION_ENDPOINT_V1)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .response

        then: 'Expect the response to be successful'
        response.status == HttpStatus.CREATED.value()
    }

    def "Update an action description"() {
        given: 'A request to updated the description on an action'
        setActionsUploadRequestPayloadValues(updateReqPayload)
        ActionDTO dto = getActionDTOMock()
        actionService.updateAction(_ as Map<String, String>) >> dto

        when: 'The update endpoint is called'
        def response = mockMvc.perform(
                MockMvcRequestBuilders.patch(ACTION_ENDPOINT_V1)
                        .content(objectMapper.writeValueAsString(updateReqPayload))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .response

        then: 'Expect the response to be successful'
        response.status == HttpStatus.OK.value()
    }

}
