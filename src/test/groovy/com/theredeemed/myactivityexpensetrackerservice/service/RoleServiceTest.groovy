package com.theredeemed.myactivityexpensetrackerservice.service

import com.theredeemed.myactivityexpensetrackerservice.exception.ActivityException
import com.theredeemed.myactivityexpensetrackerservice.model.dto.RoleDTO
import com.theredeemed.myactivityexpensetrackerservice.model.dto.Roles
import com.theredeemed.myactivityexpensetrackerservice.model.repository.RoleJdbcDAO
import spock.lang.Specification

import static com.theredeemed.myactivityexpensetrackerservice.constants.RoleTestConstants.*

class RoleServiceTest extends Specification {
    RoleService roleService
    RoleJdbcDAO roleJdbcDAO
    Map<String, String> updateReqPayload

    def setup() {
        roleJdbcDAO = Mock()
        updateReqPayload = new HashMap<>()
        roleService = new RoleService(roleJdbcDAO)
    }

    def 'Create new role'() {
        given: 'A new role needs to be created'

        when: 'The createNewRole method is called'
        RoleDTO newRole = roleService.createNewRole(getNewRoleRequest())

        then: 'Add and return the newly created role'
        1 * roleJdbcDAO.create(_ as RoleDTO)
        newRole.roleName == Roles.USER
        newRole.description.equalsIgnoreCase('Test Role')
    }

    def 'Create new role - exception'() {
        given: 'A new role needs to be created'

        when: 'The createNewRole method is called'
        roleService.createNewRole(getNewRoleRequest())

        and: 'An Exception occurs'

        then: 'Except an exception to be thrown'
        1 * roleJdbcDAO.create(_ as RoleDTO) >> { throw new IllegalArgumentException() }
        def exception = thrown(ActivityException)
        exception.error.code == 0
        exception.error.description.equalsIgnoreCase("Unable to save record")
    }

    def 'Get list of all roles'() {
        given: 'A list of all roles is requested'

        when: 'The findAllRoles method is called'
        List<RoleDTO> roleDTOList = roleService.findAllRoles()

        then: 'Return a list of all roles'
        1 * roleJdbcDAO.findAll() >> getListOfRoles()
        roleDTOList.size() > 0
    }

    def 'Get a role by ID'() {
        given: 'A Role is requested'

        when: 'The findRoleById method is called'
        RoleDTO role = roleService.findRoleById(1)

        then: 'Return the role'
        1 * roleJdbcDAO.findById(_ as Long) >> Optional.of(getRoleMockObj())
        role.id == 1
        role.roleName == Roles.USER
    }

    def 'Get a role by ID - Exception'() {
        given: 'A Role is requested'

        when: 'The findRoleById method is called and the role is not found'
        roleService.findRoleById(1)

        then: 'An exception will be thrown'
        1 * roleJdbcDAO.findById(_ as Long) >> Optional.empty()
        def exception = thrown(ActivityException)
        exception.error.code == 1
        exception.error.description.equalsIgnoreCase('Record Not Found')
    }

    def "Update Role"() {
        given: 'A Request to update a role'
        setRoleUploadRequestPayloadValues(updateReqPayload)

        when: 'The updateAction method is called with an updateRequestPayload'
        RoleDTO dto = roleService.updateRole(updateReqPayload)

        then: 'Return a list of all actions'
        1 * roleJdbcDAO.findById(_ as Long) >> Optional.of(getRoleMockObj())
        1 * roleJdbcDAO.update(_ as RoleDTO, _ as Long)
        dto.roleName == Roles.USER
        dto.description.equalsIgnoreCase("some updated description")
    }

    def "Update Role - Invalid update request payload - id is null"() {
        given: 'A Request to update a Role'

        when: 'The updateRole method is called with an updateRequestPayload'
        roleService.updateRole(updateReqPayload)

        then: 'An Exception is returned'
        def exception = thrown(ActivityException)
        exception.error.code == 4
        exception.error.description.equalsIgnoreCase("Invalid Request Payload")
    }

    def "Update Role - Invalid update request payload - description is null"() {
        given: 'A Request to update a Role'
        updateReqPayload.put("id", "1")

        when: 'The updateRole method is called with an updateRequestPayload'
        roleService.updateRole(updateReqPayload)

        then: 'An Exception is returned'
        def exception = thrown(ActivityException)
        exception.error.code == 4
        exception.error.description.equalsIgnoreCase("Invalid Request Payload")
    }

    def "Update Role - Exception while updating the record"() {
        given: 'A Request to update a Role'
        setRoleUploadRequestPayloadValues(updateReqPayload)

        when: 'The updateRole method is called with an updateRequestPayload'
        roleService.updateRole(updateReqPayload)

        then: 'An Exception is returned'
        1 * roleJdbcDAO.findById(_ as Long) >> Optional.of(getRoleMockObj())
        1 * roleJdbcDAO.update(_ as RoleDTO, _ as Long) >> { throw new IllegalArgumentException() }
        def exception = thrown(ActivityException)
        exception.error.code == 2
        exception.error.description.equalsIgnoreCase("Unable to update record")
    }
}
