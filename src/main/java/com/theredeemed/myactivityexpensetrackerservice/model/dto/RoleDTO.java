package com.theredeemed.myactivityexpensetrackerservice.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleDTO implements Serializable {
    public static final long serialVersionUID = 635336854742360713L;

    private Long id;
    private String roleName;
    private String description;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleDTO)) return false;
        RoleDTO that = (RoleDTO) o;
        return id.equals(that.id) &&
                roleName.equals(that.roleName) &&
                description.equals(that.description) &&
                createdBy.equals(that.createdBy) &&
                createdDate.equals(that.createdDate) &&
                updatedBy.equals(that.updatedBy) &&
                updatedDate.equals(that.updatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName, description, createdBy, createdDate, updatedBy, updatedDate);
    }
}
