package com.theredeemed.myactivityexpensetrackerservice.controller;

import com.theredeemed.myactivityexpensetrackerservice.model.dto.ActivityDto;
import com.theredeemed.myactivityexpensetrackerservice.service.ActivityExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/activity-expense")
@CrossOrigin(origins = "http://localhost:3000")
public class ActivityExpenseController {

    private final ActivityExpenseService activityExpenseService;

    @Autowired
    public ActivityExpenseController(ActivityExpenseService activityExpenseService) {
        this.activityExpenseService = activityExpenseService;
    }

    @GetMapping
    public ResponseEntity<List<ActivityDto>> retrieveActivityList() {
        List<ActivityDto> activityList = activityExpenseService.getActivityList();
        return new ResponseEntity<>(activityList, HttpStatus.OK);
    }
}
