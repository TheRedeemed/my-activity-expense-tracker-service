package com.theredeemed.myactivityexpensetrackerservice.service;

import com.theredeemed.myactivityexpensetrackerservice.exception.ActivityException;
import com.theredeemed.myactivityexpensetrackerservice.model.dto.ActivityDto;
import com.theredeemed.myactivityexpensetrackerservice.model.entity.ActivityEntity;
import com.theredeemed.myactivityexpensetrackerservice.model.repository.ActivityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static com.theredeemed.myactivityexpensetrackerservice.converter.ActivityConverter.*;
import static com.theredeemed.myactivityexpensetrackerservice.exception.Error.UNABLE_TO_SAVE_ACTIVITY;
import static com.theredeemed.myactivityexpensetrackerservice.exception.Error.UNABLE_TO_UPDATE_ACTIVITY_BALANCE;

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

    public ActivityDto createNewActivity(ActivityDto newActivityDto) throws ActivityException {
        ActivityEntity newActivityEntity = toActivityEntity(newActivityDto);
        try {
            log.debug("Saving activity entity");
            activityRepository.save(newActivityEntity);
            return newActivityDto;
        } catch (IllegalArgumentException | DataAccessException e) {
            log.error(e.getMessage());
            throw new ActivityException(UNABLE_TO_SAVE_ACTIVITY, e);
        }
    }

    @Transactional  //A Transaction is required when perform a Db update operation
    public ActivityDto updateActivityBalance(Map<String, String> activityExpense) throws ActivityException {
        String title = activityExpense.get("title");
        BigDecimal newBalance = new BigDecimal(activityExpense.get("balance"));
        try{
            activityRepository.updateActivityBalance(title, newBalance);
            return ActivityDto.builder().title(title).balance(newBalance).build();
        } catch (IllegalArgumentException | DataAccessException e) {
            log.error(e.getMessage());
            throw new ActivityException(UNABLE_TO_UPDATE_ACTIVITY_BALANCE, e);
        }
    }
}
