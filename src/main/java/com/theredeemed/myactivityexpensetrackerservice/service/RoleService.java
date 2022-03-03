package com.theredeemed.myactivityexpensetrackerservice.service;

import com.theredeemed.myactivityexpensetrackerservice.exception.ActivityException;
import com.theredeemed.myactivityexpensetrackerservice.exception.Error;
import com.theredeemed.myactivityexpensetrackerservice.model.dto.RoleDTO;
import com.theredeemed.myactivityexpensetrackerservice.model.repository.RoleJdbcDAO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.theredeemed.myactivityexpensetrackerservice.exception.Error.*;

@Service
@Slf4j
public class RoleService {

    private final RoleJdbcDAO roleJdbcDAO;

    public RoleService(RoleJdbcDAO roleJdbcDAO) {
        this.roleJdbcDAO = roleJdbcDAO;
    }

    public RoleDTO createNewRole(RoleDTO dto) throws ActivityException {
        dto.setCreatedDate(LocalDate.now());
        dto.setUpdatedDate(LocalDate.now());
        try{
            log.debug("Creating new role {}", dto);
            roleJdbcDAO.create(dto);
            return dto;
        } catch(IllegalArgumentException | DataAccessException e) {
            log.error("Error while saving new role - {}",e.getMessage());
            throw new ActivityException(Error.UNABLE_TO_SAVE_RECORD, e);
        }
    }

    public List<RoleDTO> findAllRoles() {
        log.debug("Returning list of all roles");
        return roleJdbcDAO.findAll();
    }

    public RoleDTO findRoleById(Long id) throws ActivityException {
        log.debug("Getting role with id : {}", id);
        Optional<RoleDTO> role = roleJdbcDAO.findById(id);
        role.orElseThrow(() -> new ActivityException(RECORD_NOT_FOUND));
        return role.get();
    }

    public RoleDTO updateRole(Map<String, String> updateRequestPayload) throws ActivityException {
        validateUpdateRequestPayload(updateRequestPayload);
        Long roleId = Long.parseLong(updateRequestPayload.get("id"));
        RoleDTO dto = findRoleById(roleId);
        dto.setDescription(updateRequestPayload.get("description"));
        dto.setUpdatedBy("SYSTEM"); //TODO - replace this value user updating the record
        dto.setUpdatedDate(LocalDate.now());
        try {
            log.debug("Updating role with id : {} with new data: {}", roleId, dto);
            roleJdbcDAO.update(dto, roleId);
            return dto;
        } catch (IllegalArgumentException | DataAccessException e) {
            log.error(e.getMessage());
            throw new ActivityException(UNABLE_TO_UPDATE_RECORD);
        }
    }

    private void validateUpdateRequestPayload(Map<String, String> updateRequestPayload) throws ActivityException {
        if(StringUtils.isBlank(updateRequestPayload.get("id")) ||
                StringUtils.isBlank(updateRequestPayload.get("description")))
            throw new ActivityException(INVALID_REQUEST_PAYLOAD);

    }

}
