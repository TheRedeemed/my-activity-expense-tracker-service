package com.theredeemed.myactivityexpensetrackerservice.controller;

import com.theredeemed.myactivityexpensetrackerservice.model.dto.ActivityDto;
import com.theredeemed.myactivityexpensetrackerservice.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class ActivityController {

    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<ActivityDto>> retrieveActivityList() {
        log.debug("Calling activity service to retrieve Activity List");
        List<ActivityDto> activityList = activityService.getActivityList();
        log.debug("Returning activity list from activity service : {}", activityList);
        return new ResponseEntity<>(activityList, HttpStatus.OK);
    }

    @PostMapping(value = "/new")
    public ResponseEntity<ActivityDto> createActivity(@RequestBody @Validated ActivityDto newActivity) {
        log.debug("Calling activity service to create activity : {}", newActivity);
        ActivityDto newlyCreatedActivity = activityService.createNewActivity(newActivity);
        log.debug("Retuning newly created activity : {}", newlyCreatedActivity);
        return new ResponseEntity<>(newlyCreatedActivity, HttpStatus.CREATED);
    }
}
