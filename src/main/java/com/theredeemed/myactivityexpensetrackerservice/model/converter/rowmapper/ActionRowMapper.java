package com.theredeemed.myactivityexpensetrackerservice.model.converter.rowmapper;

import com.theredeemed.myactivityexpensetrackerservice.model.dto.ActionDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActionRowMapper implements RowMapper<ActionDTO> {
    @Override
    public ActionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ActionDTO.builder()
                .id(rs.getLong("id"))
                .actionCode(rs.getString("actionCode"))
                .description(rs.getString("description"))
                .createdBy(rs.getString("createdBy"))
                .createdDate(rs.getDate("createdDate").toLocalDate())
                .updatedBy(rs.getString("updatedBy"))
                .updatedDate(rs.getDate("updatedDate").toLocalDate())
                .build();
    }
}
