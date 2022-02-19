package com.theredeemed.myactivityexpensetrackerservice.model.repository;

import com.theredeemed.myactivityexpensetrackerservice.model.dto.ActivityDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ActivityJdbcDAO implements DAO<ActivityDTO> {
    @Override
    public void create(ActivityDTO activityDTO) {

    }

    @Override
    public Optional<ActivityDTO> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<ActivityDTO> findAll() {
        return null;
    }

    @Override
    public void update(ActivityDTO activityDTO, Long id) {

    }

    @Override
    public void delete(Long id) {

    }
}
