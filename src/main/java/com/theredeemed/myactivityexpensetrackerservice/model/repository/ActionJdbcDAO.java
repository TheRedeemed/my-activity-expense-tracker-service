package com.theredeemed.myactivityexpensetrackerservice.model.repository;

import com.theredeemed.myactivityexpensetrackerservice.model.dto.ActionDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ActionJdbcDAO implements DAO<ActionDTO> {
    @Override
    public void create(ActionDTO actionDTO) {

    }

    @Override
    public Optional<ActionDTO> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<ActionDTO> findAll() {
        return null;
    }

    @Override
    public void update(ActionDTO actionDTO, Long id) {

    }

    @Override
    public void delete(Long id) {

    }
}
