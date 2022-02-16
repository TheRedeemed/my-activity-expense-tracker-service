package com.theredeemed.myactivityexpensetrackerservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ROLES")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RoleEntity implements Serializable {
    public static final long serialVersionUID = -3740548798456715740L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "ROLE_NAME", nullable = false, unique = true, length = 10)
    private String roleName;

    @Column(name = "DESCRIPTION", nullable = false, length = 250)
    private String description;

    @Column(name = "CREATED_BY", nullable = false, length = 20)
    private String createdBy;

    @Column(name = "CREATED_DATE", nullable = false)
    private Date createdDate;

    @Column(name = "UPDATED_BY", nullable = false, length = 20)
    private String updatedBy;

    @Column(name = "UPDATED_DATE", nullable = false)
    private Date updatedDate;

    @OneToOne(mappedBy = "roleEntity")
    private UserEntity userEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleEntity)) return false;
        RoleEntity that = (RoleEntity) o;
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
