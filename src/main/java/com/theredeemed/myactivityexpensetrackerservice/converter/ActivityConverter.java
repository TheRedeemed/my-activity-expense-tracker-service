package com.theredeemed.myactivityexpensetrackerservice.converter;

import com.theredeemed.myactivityexpensetrackerservice.model.dto.ActivityDto;
import com.theredeemed.myactivityexpensetrackerservice.model.entity.ActivityEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ActivityConverter {
    public static List<ActivityDto> toActivityDtos(List<ActivityEntity> activityEntities) {
        log.debug("Converting list of activity entities to list of activities dtos");
        return activityEntities.stream()
                .map(ActivityConverter::toActivityDto)
                .collect(Collectors.toList());
    }

    public static ActivityDto toActivityDto(ActivityEntity activityEntity) {
        log.debug("Converting activity entity to activity dto");
        return ActivityDto.builder()
                .title(activityEntity.getTitle())
                .description(activityEntity.getDescription())
                .fee(activityEntity.getFee())
                .balance(activityEntity.getBalance())
                .createdTimestamp(activityEntity.getCreatedTimestamp())
                .updatedTimestamp(activityEntity.getUpdatedTimestamp())
                .build();
    }

    public static ActivityEntity toActivityEntity(ActivityDto activityDto) {
        log.debug("Converting activity dto to activity entity");
        return ActivityEntity.builder()
                .title(activityDto.getTitle())
                .description(activityDto.getDescription())
                .fee(activityDto.getFee())
                .balance(activityDto.getBalance())
                .build();
    }
}
