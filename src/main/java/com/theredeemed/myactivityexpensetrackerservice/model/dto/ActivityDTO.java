package com.theredeemed.myactivityexpensetrackerservice.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivityDTO implements Serializable {

    public static final long serialVersionUID = 5960343296903472428L;

    private Long id;

//    @NotBlank(message = "Username is required")
    private String username;

//    @NotBlank(message = "Activity Title is required")
    private String title;

//    @NotBlank(message = "Activity Description is required")
    private String description;

//    @NotNull(message = "Activity Fee is required")
    private BigDecimal fee;

//    @NotNull(message = "Activity Balance is required")
//    private BigDecimal balance;

    private LocalDate createdDate;
    private LocalDate updatedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActivityDTO)) return false;
        ActivityDTO that = (ActivityDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(fee, that.fee) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(updatedDate, that.updatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, title, description, fee, createdDate, updatedDate);
    }

    @Override
    public String toString() {
        return "ActivityDTO{" +
                "id=" + id +
                ", username=" + username +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", fee=" + fee +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
