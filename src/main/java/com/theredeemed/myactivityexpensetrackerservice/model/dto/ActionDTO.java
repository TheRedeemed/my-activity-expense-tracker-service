package com.theredeemed.myactivityexpensetrackerservice.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActionDTO {
    private Long id;
    private String actionCode;
    private String description;
    private String createdBy;
    private LocalDate createdDate;
    private String updatedBy;
    private LocalDate updatedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActionDTO)) return false;
        ActionDTO actionDTO = (ActionDTO) o;
        return Objects.equals(id, actionDTO.id) &&
                Objects.equals(actionCode, actionDTO.actionCode) &&
                Objects.equals(description, actionDTO.description) &&
                Objects.equals(createdBy, actionDTO.createdBy) &&
                Objects.equals(createdDate, actionDTO.createdDate) &&
                Objects.equals(updatedBy, actionDTO.updatedBy) &&
                Objects.equals(updatedDate, actionDTO.updatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, actionCode, description, createdBy, createdDate, updatedBy, updatedDate);
    }

    @Override
    public String toString() {
        return "ActionDTO{" +
                "id=" + id +
                ", actionCode='" + actionCode + '\'' +
                ", description='" + description + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate=" + createdDate +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
