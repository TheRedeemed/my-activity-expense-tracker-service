package com.theredeemed.myactivityexpensetrackerservice.controller;

import com.theredeemed.myactivityexpensetrackerservice.exception.ActivityException;
import com.theredeemed.myactivityexpensetrackerservice.model.dto.ActionDTO;
import com.theredeemed.myactivityexpensetrackerservice.service.ActionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.theredeemed.myactivityexpensetrackerservice.constants.ApiConstants.ACTION_ENDPOINT_V1;

@RestController
@RequestMapping(ACTION_ENDPOINT_V1)
@Slf4j
public class ActionController {
    private final ActionService actionService;

    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }

    @PostMapping
    public ResponseEntity<ActionDTO> addNewAction(@RequestBody ActionDTO actionDTO) throws ActivityException {
        log.debug("Calling action service to create action : {}", actionDTO);
        ActionDTO newAction = actionService.addNewAction(actionDTO);
        log.debug("returning newly created action {}", newAction);
        return new ResponseEntity<>(newAction, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ActionDTO>> getAllActions() {
        log.debug("Calling action service to retrieve Action List");
        List<ActionDTO> actionDTOList = actionService.getAllActionList();
        log.debug("Returning list of all actions");
        return new ResponseEntity<>(actionDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActionDTO> getActionById(@PathVariable("id") Long id) throws ActivityException {
        log.debug("Calling action service to get action with id : {}", id);
        ActionDTO actionDTO = actionService.getActionById(id);
        log.debug("Returning action : {}", actionDTO);
        return new ResponseEntity<>(actionDTO, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<ActionDTO> updateActionDescription(@RequestBody Map<String, String> updateRequestPayload)
            throws ActivityException {
        log.debug("Calling action service to update action description : {}", updateRequestPayload);
        ActionDTO updatedAction = actionService.updateAction(updateRequestPayload);
        log.debug("Returning updated action : {}", updatedAction);
        return new ResponseEntity<>(updatedAction, HttpStatus.OK);
    }
}
