package com.theredeemed.myactivityexpensetrackerservice.service;

import com.theredeemed.myactivityexpensetrackerservice.exception.ActivityException;
import com.theredeemed.myactivityexpensetrackerservice.exception.Error;
import com.theredeemed.myactivityexpensetrackerservice.model.dto.ActionDTO;
import com.theredeemed.myactivityexpensetrackerservice.model.repository.ActionJdbcDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.theredeemed.myactivityexpensetrackerservice.constants.AppConstants.ACTION;
import static com.theredeemed.myactivityexpensetrackerservice.exception.Error.RECORD_NOT_FOUND;
import static com.theredeemed.myactivityexpensetrackerservice.exception.Error.UNABLE_TO_UPDATE_RECORD;
import static com.theredeemed.myactivityexpensetrackerservice.util.Validator.validateUpdateRequestPayload;

@Service
@Slf4j
public class ActionService {
    private final ActionJdbcDAO actionJdbcDAO;

    public ActionService(ActionJdbcDAO actionJdbcDAO) {
        this.actionJdbcDAO = actionJdbcDAO;
    }

    public ActionDTO createNewAction(ActionDTO actionDTO) throws ActivityException {
        actionDTO.setCreatedDate(LocalDate.now());
        actionDTO.setUpdatedDate(LocalDate.now());
        try {
            log.debug("Creating new action {}", actionDTO);
            actionJdbcDAO.create(actionDTO);
            return actionDTO;
        } catch (IllegalArgumentException | DataAccessException e) {
            String errorDescription = "An error occurred while saving new role";
            log.error(errorDescription + ": {}", e.getMessage());
            throw new ActivityException(Error.UNABLE_TO_SAVE_RECORD, errorDescription, e);
        }
    }

    public List<ActionDTO> getAllActionList() {
        log.debug("Getting list of all actions");
        return actionJdbcDAO.findAll();
    }

    public ActionDTO getActionById(Long id) throws ActivityException {
        log.debug("Getting action with id : {}", id);
        Optional<ActionDTO> actionDTO = actionJdbcDAO.findById(id);
        actionDTO.orElseThrow(() -> new ActivityException(RECORD_NOT_FOUND, "Action with ID " + id + " was not found"));
        return actionDTO.get();
    }

    public ActionDTO updateAction(Map<String, String> updateRequestPayload) throws ActivityException {
        validateUpdateRequestPayload(updateRequestPayload, ACTION);
        Long actionId = Long.parseLong(updateRequestPayload.get("id"));
        ActionDTO dto = getActionById(actionId);
        dto.setDescription(updateRequestPayload.get("description"));
        dto.setUpdatedBy("SYSTEM"); //TODO - replace this value user updating the record
        dto.setUpdatedDate(LocalDate.now());
        try {
            log.debug("Updating actions with id : {} with new data: {}", actionId, dto);
            actionJdbcDAO.update(dto, actionId);
            return dto;
        } catch (IllegalArgumentException | DataAccessException e) {
            String errorDescription = "An error occurred while updating a role";
            log.error(errorDescription + ": {}", e.getMessage());
            throw new ActivityException(UNABLE_TO_UPDATE_RECORD, errorDescription, e);
        }
    }
}
