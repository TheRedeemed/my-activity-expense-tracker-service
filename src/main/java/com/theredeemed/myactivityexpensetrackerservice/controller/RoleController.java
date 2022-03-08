package com.theredeemed.myactivityexpensetrackerservice.controller;

import com.theredeemed.myactivityexpensetrackerservice.exception.ActivityException;
import com.theredeemed.myactivityexpensetrackerservice.model.dto.RoleDTO;
import com.theredeemed.myactivityexpensetrackerservice.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.theredeemed.myactivityexpensetrackerservice.constants.ApiConstants.ROLE_ENDPOINT_V1;

@RestController
@Slf4j
@RequestMapping(value = ROLE_ENDPOINT_V1, produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<RoleDTO> createNewRole(@RequestBody RoleDTO roleDTO) throws ActivityException {
        log.debug("Calling action service to create role : {}", roleDTO);
        RoleDTO newRole = roleService.createNewRole(roleDTO);
        log.debug("Returning newly created roles {}", newRole);
        return new ResponseEntity<>(newRole, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        log.debug("Calling role service to retrieve role list");
        List<RoleDTO> roleDTOList = roleService.findAllRoles();
        log.debug("Returning list of all roles");
        return new ResponseEntity<>(roleDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable("id") Long id) throws ActivityException {
        log.debug("Calling role service to retrieve the role with id: {}", id);
        RoleDTO dto = roleService.findRoleById(id);
        log.debug("Retuning role");
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<RoleDTO> updateRoleDescription(@RequestBody Map<String, String> updateRequestPayload)
            throws ActivityException {
        log.debug("Calling role service to update role description : {}", updateRequestPayload);
        RoleDTO updatedRole = roleService.updateRole(updateRequestPayload);
        log.debug("Returning updated action : {}", updatedRole);
        return new ResponseEntity<>(updatedRole, HttpStatus.OK);
    }

}
