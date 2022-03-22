package com.theredeemed.myactivityexpensetrackerservice.service

import com.theredeemed.myactivityexpensetrackerservice.exception.ActivityException
import com.theredeemed.myactivityexpensetrackerservice.model.dto.ActionDTO
import com.theredeemed.myactivityexpensetrackerservice.model.repository.ActionJdbcDAO
import spock.lang.Specification

import static com.theredeemed.myactivityexpensetrackerservice.constants.ActionTestConstants.*

class ActionServiceTest extends Specification {
    ActionService actionService
    ActionJdbcDAO actionJdbcDAO
    Map<String, String> updateReqPayload

    def setup() {
        actionJdbcDAO = Mock()
        updateReqPayload = new HashMap<>()
        actionService = new ActionService(actionJdbcDAO)
    }

    def "Add a new transaction action"() {
        given: 'A Request to create a new transaction action'

        when: 'The addNewAction method is called with the correct payload'
        ActionDTO newAction = actionService.createNewAction(getNewActionReqPayload())

        then: 'Return the created action'
        1 * actionJdbcDAO.create(_)
        newAction.actionCode.equalsIgnoreCase("TR_CODE")
    }

    def "Add a new transaction action - Exception"() {
        given: 'A Request to create a new transaction action'

        when: 'The addNewAction method is called'
        actionService.createNewAction(getNewActionReqPayload())

        then: 'An exception is thrown'
        1 * actionJdbcDAO.create(_ as ActionDTO) >> { throw new IllegalArgumentException() }
        def exception = thrown(ActivityException)
        exception.error.code == 1000
        exception.error.title.equalsIgnoreCase("Unable to save record")
    }

    def "Get all action list"() {
        given: 'A Request to get list of actions'

        when: 'The getAllActionList method is called'
        List<ActionDTO> dtoList = actionService.getAllActionList()

        then: 'Return a list of all actions'
        1 * actionJdbcDAO.findAll() >> getActionListMock()
        !dtoList.isEmpty()
        ActionDTO dto = dtoList.get(0)
        dto.actionCode.equalsIgnoreCase("TR_CODE")
    }

    def "Get an action by ID"() {
        given: 'A Request to get a given action based on its ID'

        when: 'The getActionById method is called with an ID'
        ActionDTO dto = actionService.getActionById(1L)

        then: 'Return the request action'
        1 * actionJdbcDAO.findById(_ as Long) >> Optional.of(getActionDTOMock())
        dto.actionCode.equalsIgnoreCase("TR_CODE")
    }

    def "Get an action by ID - Empty result"() {
        given: 'A Request to get a given action based on its ID'

        when: 'The getActionById method is called with an ID'
        actionService.getActionById(1L)

        then: 'Return a list of all actions'
        1 * actionJdbcDAO.findById(_ as Long) >> Optional.empty()
        def exception = thrown(ActivityException)
        exception.error.code == 1001
        exception.error.title.equalsIgnoreCase("Record Not Found")
    }

    def "Update Action"() {
        given: 'A Request to update an action'
        setActionsUploadRequestPayloadValues(updateReqPayload)

        when: 'The updateAction method is called with an updateRequestPayload'
        ActionDTO dto = actionService.updateAction(updateReqPayload)

        then: 'Return a list of all actions'
        1 * actionJdbcDAO.findById(_ as Long) >> Optional.of(getActionDTOMock())
        1 * actionJdbcDAO.update(_ as ActionDTO, _ as Long)
        dto.actionCode.equalsIgnoreCase("TR_CODE")
        dto.description.equalsIgnoreCase("some description")
    }

    def "Update Action - Invalid update request payload - id is null"() {
        given: 'A Request to update an action'

        when: 'The updateAction method is called with an updateRequestPayload'
        actionService.updateAction(updateReqPayload)

        then: 'An Exception is returned'
        def exception = thrown(ActivityException)
        exception.error.code == 1004
        exception.error.title.equalsIgnoreCase("Invalid Request Payload")
    }

    def "Update Action - Invalid update request payload - description is null"() {
        given: 'A Request to update an action'
        updateReqPayload.put("id", "1")

        when: 'The updateAction method is called with an updateRequestPayload'
        actionService.updateAction(updateReqPayload)

        then: 'An Exception is returned'
        def exception = thrown(ActivityException)
        exception.error.code == 1004
        exception.error.title.equalsIgnoreCase("Invalid Request Payload")
    }

    def "Update Action - Exception while updating the record"() {
        given: 'A Request to update an action'
        setActionsUploadRequestPayloadValues(updateReqPayload)

        when: 'The updateAction method is called with an updateRequestPayload'
        actionService.updateAction(updateReqPayload)

        then: 'An Exception is returned'
        1 * actionJdbcDAO.findById(_ as Long) >> Optional.of(getActionDTOMock())
        1 * actionJdbcDAO.update(_ as ActionDTO, _ as Long) >> { throw new IllegalArgumentException() }
        def exception = thrown(ActivityException)
        exception.error.code == 1002
        exception.error.title.equalsIgnoreCase("Unable to update record")
    }
}
