package com.theredeemed.myactivityexpensetrackerservice.model.converter.rowmapper;

import com.theredeemed.myactivityexpensetrackerservice.model.dto.ActionDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ActionRowMapper implements RowMapper<ActionDTO> {
    @Override
    public ActionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ActionDTO.builder()
                .id(rs.getLong("id"))
                .actionCode(rs.getString("action_code"))
                .description(rs.getString("description"))
                .createdBy(rs.getString("created_by"))
                .createdDate(rs.getDate("created_date").toLocalDate())
                .updatedBy(rs.getString("updated_by"))
                .updatedDate(rs.getDate("updated_date").toLocalDate())
                .build();
    }
}
