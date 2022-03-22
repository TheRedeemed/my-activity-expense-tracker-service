package com.theredeemed.myactivityexpensetrackerservice.model.repository;

import com.theredeemed.myactivityexpensetrackerservice.exception.ActivityException;
import com.theredeemed.myactivityexpensetrackerservice.model.converter.rowmapper.RoleRowMapper;
import com.theredeemed.myactivityexpensetrackerservice.model.dto.RoleDTO;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.theredeemed.myactivityexpensetrackerservice.exception.Error.RECORD_NOT_FOUND;
import static com.theredeemed.myactivityexpensetrackerservice.model.sql.RoleSQL.*;

@Repository
public class RoleJdbcDAO implements DAO<RoleDTO> {

    private final JdbcTemplate jdbcTemplate;
    private final RoleRowMapper roleRowMapper;

    public RoleJdbcDAO(JdbcTemplate jdbcTemplate, RoleRowMapper roleRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.roleRowMapper = roleRowMapper;
    }

    @Override
    public void create(RoleDTO roleDTO) {
        jdbcTemplate.update(INSERT_ROLE_SQL, roleDTO.getRoleName(), roleDTO.getDescription(), roleDTO.getCreatedBy(),
                roleDTO.getCreatedDate(), roleDTO.getUpdatedBy(), roleDTO.getUpdatedDate());
    }

    @Override
    public Optional<RoleDTO> findById(Long id) throws ActivityException {
        try {
            RoleDTO roleDTO = jdbcTemplate.queryForObject(FIND_ROLE_BY_ID_SQL, roleRowMapper, id);
            return Optional.ofNullable(roleDTO);
        } catch (DataAccessException e) {
            throw new ActivityException(RECORD_NOT_FOUND, "Role with ID " + id + " was not found");
        }
    }

    @Override
    public List<RoleDTO> findAll() {
        return jdbcTemplate.query(FIND_ALL_ROLES_SQL, roleRowMapper);
    }

    @Override
    public void update(RoleDTO roleDTO, Long id) {
        jdbcTemplate.update(UPDATE_ROLE_SQL, roleDTO.getDescription(), roleDTO.getUpdatedBy(),
                roleDTO.getUpdatedDate(), id);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE_ROLE_SQL, id);
    }
}
