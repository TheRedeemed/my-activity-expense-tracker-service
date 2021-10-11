package com.theredeemed.myactivityexpensetrackerservice.controller;

import com.theredeemed.myactivityexpensetrackerservice.exception.ActivityException;
import com.theredeemed.myactivityexpensetrackerservice.model.dto.ActivityDto;
import com.theredeemed.myactivityexpensetrackerservice.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.theredeemed.myactivityexpensetrackerservice.constants.ApiConstants.ACTIVITY_ENDPOINT_V1;

@RestController
@RequestMapping(ACTIVITY_ENDPOINT_V1)
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class ActivityController {

    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    public ResponseEntity<List<ActivityDto>> retrieveActivityList() {
        log.debug("Calling activity service to retrieve Activity List");
        List<ActivityDto> activityList = activityService.getActivityList();
        log.debug("Returning activity list from activity service : {}", activityList);
        return new ResponseEntity<>(activityList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ActivityDto> createActivity(@Valid @RequestBody ActivityDto newActivity) throws ActivityException {
        log.debug("Calling activity service to create activity : {}", newActivity);
        ActivityDto newlyCreatedActivity = activityService.createNewActivity(newActivity);
        log.debug("Retuning newly created activity : {}", newlyCreatedActivity);
        return new ResponseEntity<>(newlyCreatedActivity, HttpStatus.CREATED);
    }
}
