package com.theredeemed.myactivityexpensetrackerservice.model.repository;

import com.theredeemed.myactivityexpensetrackerservice.model.dto.UserDTO;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public class UserJdbcDAO implements DAO<UserDTO> {
    private final JdbcTemplate jdbcTemplate;

    public UserJdbcDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(UserDTO userDTO) {

    }

    @Override
    public Optional<UserDTO> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<UserDTO> findAll() {
        return null;
    }

    @Override
    public void update(UserDTO userDTO, Long id) {

    }

    @Override
    public void delete(Long id) {

    }
}
