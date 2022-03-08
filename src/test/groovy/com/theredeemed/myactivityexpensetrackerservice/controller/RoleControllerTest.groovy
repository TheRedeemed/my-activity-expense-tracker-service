package com.theredeemed.myactivityexpensetrackerservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.theredeemed.myactivityexpensetrackerservice.model.dto.RoleDTO
import com.theredeemed.myactivityexpensetrackerservice.service.RoleService
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

import static com.theredeemed.myactivityexpensetrackerservice.constants.ApiConstants.ROLE_ENDPOINT_V1
import static com.theredeemed.myactivityexpensetrackerservice.constants.RoleTestConstants.*

@WebMvcTest(RoleController)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class RoleControllerTest extends Specification {

    @Autowired
    MockMvc mockMvc

    @SpringBean
    RoleService roleService = Mock()

    ObjectMapper objectMapper
    Map<String, String> updateReqPayload

    def setup() {
        objectMapper = new ObjectMapper()
        updateReqPayload = new HashMap<>()
    }

    def "Create a new Role"() {
        given: 'A new role need to be created'
        roleService.createNewRole(_ as RoleDTO) >> getRoleMockObj()

        when: 'The create endpoint is called'
        def response = mockMvc.perform(
                MockMvcRequestBuilders.post(ROLE_ENDPOINT_V1)
                        .content(objectMapper.writeValueAsString(getNewRoleRequest()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .response

        then: 'Expect the response to be successful'
        response.status == HttpStatus.CREATED.value()
    }

    def "Retrieve all roles"() {
        given: 'The list of roles is requested'
        roleService.findAllRoles() >> getListOfRoles()

        when: 'The getAllRoles endpoint is called'
        def response = mockMvc.perform(
                MockMvcRequestBuilders.get(ROLE_ENDPOINT_V1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .response

        then: 'Expect the response to be successful'
        response.status == HttpStatus.OK.value()
    }

    def "Retrieve a Role by ID"() {
        given: 'A Role is requested'
        roleService.findRoleById(_ as Long) >> getRoleMockObj()

        when: 'The getRoleById endpoint is called'
        def response = mockMvc.perform(
                MockMvcRequestBuilders.get(ROLE_ENDPOINT_V1 + "/{id}", "1")
                        .accept(MediaType.APPLICATION_JSON)).
                andDo(MockMvcResultHandlers.print())
                .andReturn()
                .response

        then: 'Except the response to be successful'
        response.status == HttpStatus.OK.value()
    }

    def "Update an role description"() {
        given: 'A request to updated the description on an action'
        setRoleUploadRequestPayloadValues(updateReqPayload)
        RoleDTO dto = getRoleMockObj()
        roleService.updateRole(_ as Map<String, String>) >> dto

        when: 'The update endpoint is called'
        def response = mockMvc.perform(
                MockMvcRequestBuilders.patch(ROLE_ENDPOINT_V1)
                        .content(objectMapper.writeValueAsString(updateReqPayload))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .response

        then: 'Expect the response to be successful'
        response.status == HttpStatus.OK.value()
    }

}
