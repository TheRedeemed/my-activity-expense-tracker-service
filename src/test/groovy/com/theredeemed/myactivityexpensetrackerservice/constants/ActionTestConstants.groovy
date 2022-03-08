package com.theredeemed.myactivityexpensetrackerservice.constants

import com.theredeemed.myactivityexpensetrackerservice.model.dto.ActionDTO

class ActionTestConstants {
    static ActionDTO getNewActionReqPayload() {
        return getActionDTOMock()
    }

    static List<ActionDTO> getActionListMock() {
        return [getActionDTOMock()]
    }

    static ActionDTO getActionDTOMock() {
        return ActionDTO.builder()
                .actionCode("TR_CODE")
                .description("This is a test action")
                .createdBy("USER")
                .updatedBy("USER")
                .build()
    }

    static void setActionsUploadRequestPayloadValues(Map<String, String> updateReqPayload) {
        updateReqPayload.put("id", "1")
        updateReqPayload.put("description", "some description")
    }
}
