package com.theredeemed.myactivityexpensetrackerservice.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityDto implements Serializable {

    public static final long serialVersionUID = 5960343296903472428L;

    @NotBlank(message = "Activity Title is required")
    String title;

    @NotBlank(message = "Activity Description is required")
    String description;

    @NotNull(message = "Activity Fee is required")
    BigDecimal fee;

    @NotNull(message = "Activity Balance is required")
    BigDecimal balance;

    LocalDateTime createdTimestamp;

    LocalDateTime updatedTimestamp;
}
