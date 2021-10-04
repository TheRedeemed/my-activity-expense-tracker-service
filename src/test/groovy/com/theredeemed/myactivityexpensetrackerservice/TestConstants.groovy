package com.theredeemed.myactivityexpensetrackerservice

import com.theredeemed.myactivityexpensetrackerservice.model.dto.ActivityDto
import com.theredeemed.myactivityexpensetrackerservice.model.entity.ActivityEntity

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class TestConstants {
    static List<ActivityEntity> getActivityEntityList() {
        [
                ActivityEntity.builder()
                        .id(1L)
                        .title("Martial Arts")
                        .description("Martial art activity with Mark. Every Monday at 6pm")
                        .fee(new BigDecimal("10"))
                        .balance(new BigDecimal("0"))
                        .createdTimestamp(LocalDateTime.of(LocalDate.of(2020, 12, 1), LocalTime.of(20, 15)))
                        .updatedTimestamp(LocalDateTime.of(LocalDate.of(2020, 12, 8), LocalTime.of(23, 20)))
                        .build(),
                ActivityEntity.builder()
                        .title("Workout")
                        .description("Workout with Andrew. Every Tuesday and Thursday at 7:30am")
                        .fee(new BigDecimal("15"))
                        .balance(new BigDecimal("15"))
                        .createdTimestamp(LocalDateTime.of(LocalDate.of(2020, 12, 2), LocalTime.of(15, 30)))
                        .updatedTimestamp(LocalDateTime.of(LocalDate.of(2020, 12, 24), LocalTime.of(9, 30)))
                        .build()
        ]
    }

    static ActivityDto getActivityDto() {
        return ActivityDto.builder()
                .title("Martial Arts")
                .description("Martial art activity with Mark. Every Monday at 6pm")
                .fee(new BigDecimal("10"))
                .balance(new BigDecimal("0"))
                .createdTimestamp(LocalDateTime.of(LocalDate.of(2020, 12, 1), LocalTime.of(20, 15)))
                .updatedTimestamp(LocalDateTime.of(LocalDate.of(2020, 12, 8), LocalTime.of(23, 20)))
                .build()
    }
}
