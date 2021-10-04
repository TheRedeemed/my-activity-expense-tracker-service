package com.theredeemed.myactivityexpensetrackerservice.service;

import com.theredeemed.myactivityexpensetrackerservice.model.dto.ActivityDto;
import com.theredeemed.myactivityexpensetrackerservice.model.entity.ActivityEntity;
import com.theredeemed.myactivityexpensetrackerservice.model.repository.ActivityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.theredeemed.myactivityexpensetrackerservice.converter.ActivityConverter.*;

@Service
@Slf4j
public class ActivityService {
    private final ActivityRepository activityRepository;

    @Autowired
    ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<ActivityDto> getActivityList() {
        log.debug("Returning list of activities");
        return toActivityDtos(activityRepository.findAll());
    }

    public ActivityDto createNewActivity(ActivityDto newActivityDto) {
        ActivityEntity newActivityEntity = toActivityEntity(newActivityDto);
        try {
            log.debug("Saving activity entity");
            activityRepository.save(newActivityEntity);
            return newActivityDto;
        } catch (IllegalArgumentException | DataAccessException e) {
            log.error(e.getMessage());
        }
        return ActivityDto.builder().build();
    }
}
