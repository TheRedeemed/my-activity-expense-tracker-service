package com.theredeemed.myactivityexpensetrackerservice.service;

import com.theredeemed.myactivityexpensetrackerservice.model.dto.ActivityDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ActivityExpenseService {
    static List<ActivityDto> activityList = new ArrayList<>(
            Arrays.asList(
                    ActivityDto.builder()
                            .title("Martial Arts")
                            .description("Martial art activity with Mark. Every Monday at 6pm")
                            .balance(new BigDecimal("0"))
                            .updatedTimestamp(LocalDateTime.of(LocalDate.of(2020, 12, 8), LocalTime.of(23, 20)))
                            .build(),
                    ActivityDto.builder()
                            .title("Workout")
                            .description("Workout with Andrew. Every Tuesday and Thursday at 7:30am")
                            .balance(new BigDecimal("15"))
                            .updatedTimestamp(LocalDateTime.of(LocalDate.of(2020, 12, 24), LocalTime.of(9, 30)))
                            .build()
            )
    );

    public List<ActivityDto> getActivityList() {
        return activityList;
    }
}
