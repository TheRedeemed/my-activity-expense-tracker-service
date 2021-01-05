package com.theredeemed.myactivityexpensetrackerservice.service;

import com.theredeemed.myactivityexpensetrackerservice.converter.ActivityConverter;
import com.theredeemed.myactivityexpensetrackerservice.model.dto.ActivityDto;
import com.theredeemed.myactivityexpensetrackerservice.model.entity.ActivityEntity;
import com.theredeemed.myactivityexpensetrackerservice.model.repository.ActivityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class ActivityService {
//    static List<ActivityDto> activityList = new ArrayList<>(
//            Arrays.asList(
//                    ActivityDto.builder()
//                            .title("Martial Arts")
//                            .description("Martial art activity with Mark. Every Monday at 6pm")
//                            .fee(new BigDecimal("10"))
//                            .balance(new BigDecimal("0"))
//                            .updatedTimestamp(LocalDateTime.of(LocalDate.of(2020, 12, 8), LocalTime.of(23, 20)))
//                            .build(),
//                    ActivityDto.builder()
//                            .title("Workout")
//                            .description("Workout with Andrew. Every Tuesday and Thursday at 7:30am")
//                            .fee(new BigDecimal("15"))
//                            .balance(new BigDecimal("15"))
//                            .updatedTimestamp(LocalDateTime.of(LocalDate.of(2020, 12, 24), LocalTime.of(9, 30)))
//                            .build()
//            )
//    );

    private final ActivityRepository activityRepository;

    @Autowired
    ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<ActivityDto> getActivityList() {
        log.debug("Returning list of activities");
        return ActivityConverter.toActivityDtos(activityRepository.findAll());
    }

    public ActivityDto createNewActivity(ActivityDto newActivity) {
        ActivityEntity activityEntity = ActivityConverter.toActivityEntity(newActivity);
        log.debug("Saving activity entity");
        activityRepository.save(activityEntity);
        return ActivityConverter.toActivityDto(activityEntity);
    }
}
