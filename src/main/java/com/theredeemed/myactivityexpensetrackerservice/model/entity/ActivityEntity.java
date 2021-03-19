package com.theredeemed.myactivityexpensetrackerservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ACTIVITY")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false, unique = true)
    Long id;

    @Column(name = "TITLE", nullable = false)
    String title;

    @Column(name = "DESCRIPTION", nullable = false)
    String description;

    @Column(name = "FEE", nullable = false)
    BigDecimal fee;

    @Column(name = "BALANCE", nullable = false)
    BigDecimal balance;

    @Column(name = "CREATED_TIMESTAMP", nullable = false)
    @CreationTimestamp
    LocalDateTime createdTimestamp;

    @Column(name = "UPDATED_TIMESTAMP", nullable = false)
    @UpdateTimestamp
    LocalDateTime updatedTimestamp;
}
