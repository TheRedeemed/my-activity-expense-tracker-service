package com.theredeemed.myactivityexpensetrackerservice.model.repository;

import com.theredeemed.myactivityexpensetrackerservice.model.entity.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityEntity, Long> {
    //The @Modifying annotation let spring-data know that the query is not a query to select but to update values
    // Modifying queries can only use void or int/Integer as return type!
    @Modifying
    @Query(value = "update ActivityEntity a set a.balance = :balance where a.title = :title")
    void updateActivityBalance(@Param("title") String title, @Param("balance") BigDecimal balance);
}
