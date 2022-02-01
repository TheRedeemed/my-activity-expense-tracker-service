package com.theredeemed.myactivityexpensetrackerservice.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivityDto implements Serializable {

    public static final long serialVersionUID = 5960343296903472428L;

    @NotBlank(message = "Activity Title is required")
    private String title;

    @NotBlank(message = "Activity Description is required")
    private String description;

    @NotNull(message = "Activity Fee is required")
    private BigDecimal fee;

    @NotNull(message = "Activity Balance is required")
    private BigDecimal balance;

    private LocalDateTime createdTimestamp;

    private LocalDateTime updatedTimestamp;
}
