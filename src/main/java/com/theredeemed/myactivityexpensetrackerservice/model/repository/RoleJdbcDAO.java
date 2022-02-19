package com.theredeemed.myactivityexpensetrackerservice.model.repository;

import com.theredeemed.myactivityexpensetrackerservice.model.dto.RoleDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoleJdbcDAO implements DAO<RoleDTO> {

    @Override
    public void create(RoleDTO roleDTO) {

    }

    @Override
    public Optional<RoleDTO> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<RoleDTO> findAll() {
        return null;
    }

    @Override
    public void update(RoleDTO roleDTO, Long id) {

    }

    @Override
    public void delete(Long id) {

    }
}
