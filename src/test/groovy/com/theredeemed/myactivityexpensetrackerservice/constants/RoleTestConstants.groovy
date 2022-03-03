package com.theredeemed.myactivityexpensetrackerservice.constants

import com.theredeemed.myactivityexpensetrackerservice.model.dto.RoleDTO
import com.theredeemed.myactivityexpensetrackerservice.model.dto.Roles

import java.time.LocalDate

class RoleTestConstants {

    static RoleDTO getNewRoleRequest() {
        return RoleDTO.builder()
                .roleName(Roles.USER)
                .description('Test Role')
                .createdBy('TESTER')
                .updatedBy('TESTER')
                .build()
    }

    static RoleDTO getRoleMockObj() {
        RoleDTO dto = getNewRoleRequest()
        dto.setId(1L)
        dto.setCreatedDate(LocalDate.now())
        dto.setUpdatedDate(LocalDate.now())
        return dto
    }

    static List<RoleDTO> getListOfRoles() {
        return [getRoleMockObj()]
    }

    static void setRoleUploadRequestPayloadValues(Map<String, String> updateReqPayload) {
        updateReqPayload.put("id", "1")
        updateReqPayload.put("description", "some updated description")
    }
}
