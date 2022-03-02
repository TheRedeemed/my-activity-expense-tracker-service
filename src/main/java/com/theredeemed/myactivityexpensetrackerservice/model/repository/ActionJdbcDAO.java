package com.theredeemed.myactivityexpensetrackerservice.model.repository;

import com.theredeemed.myactivityexpensetrackerservice.exception.ActivityException;
import com.theredeemed.myactivityexpensetrackerservice.model.converter.rowmapper.ActionRowMapper;
import com.theredeemed.myactivityexpensetrackerservice.model.dto.ActionDTO;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.theredeemed.myactivityexpensetrackerservice.exception.Error.RECORD_NOT_FOUND;
import static com.theredeemed.myactivityexpensetrackerservice.model.sql.ActionSQL.*;

@Repository
public class ActionJdbcDAO implements DAO<ActionDTO> {
    private final JdbcTemplate jdbcTemplate;
    private final ActionRowMapper actionRowMapper;

    public ActionJdbcDAO(JdbcTemplate jdbcTemplate, ActionRowMapper actionRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.actionRowMapper = actionRowMapper;
    }

    @Override
    public void create(ActionDTO dto) {
        jdbcTemplate.update(INSERT_ACTION_SQL, dto.getActionCode(), dto.getDescription(), dto.getCreatedBy(),
                dto.getCreatedDate(), dto.getUpdatedBy(), dto.getUpdatedDate());
    }

    @Override
    public Optional<ActionDTO> findById(Long id) throws ActivityException {
        try{
            ActionDTO dto = jdbcTemplate.queryForObject(FIND_ACTION_BY_ID_SQL, actionRowMapper, id);
            return Optional.ofNullable(dto);
        } catch (DataAccessException e) {
            throw new ActivityException(RECORD_NOT_FOUND);
        }
    }

    @Override
    public List<ActionDTO> findAll() {
        return jdbcTemplate.query(FIND_ALL_ACTIONS_SQL, actionRowMapper);
    }

    @Override
    public void update(ActionDTO dto, Long id) {
        jdbcTemplate.update(UPDATE_ACTION_SQL, dto.getDescription(), dto.getUpdatedBy(), dto.getUpdatedDate(), id);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE_ACTION_SQL, id);
    }
}
