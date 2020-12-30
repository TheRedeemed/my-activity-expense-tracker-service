package com.theredeemed.myactivityexpensetrackerservice.converter;

import com.theredeemed.myactivityexpensetrackerservice.model.dto.ActivityDto;
import com.theredeemed.myactivityexpensetrackerservice.model.entity.ActivityEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ActivityConverter {
    public static List<ActivityDto> toActivityDtos(List<ActivityEntity> activityEntities) {
        return activityEntities.stream()
                .map(
                        activityEntity -> ActivityDto.builder()
                                .title(activityEntity.getTitle())
                                .description(activityEntity.getDescription())
                                .fee(activityEntity.getFee())
                                .balance(activityEntity.getBalance())
                                .updatedTimestamp(activityEntity.getUpdatedTimestamp())
                                .build()
                ).collect(Collectors.toList());
    }
}
