package com.theredeemed.myactivityexpensetrackerservice.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityDto {
    String title;
    String description;
    BigDecimal fee;
    BigDecimal balance;
    LocalDateTime updatedTimestamp;
}
