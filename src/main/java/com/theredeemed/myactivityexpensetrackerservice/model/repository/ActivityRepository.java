package com.theredeemed.myactivityexpensetrackerservice.model.repository;

import com.theredeemed.myactivityexpensetrackerservice.model.entity.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityEntity, Long> {
}
