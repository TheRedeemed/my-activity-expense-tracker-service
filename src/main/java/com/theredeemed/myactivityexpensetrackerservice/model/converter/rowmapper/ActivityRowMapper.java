package com.theredeemed.myactivityexpensetrackerservice.model.converter.rowmapper;

import com.theredeemed.myactivityexpensetrackerservice.model.dto.ActivityDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivityRowMapper implements RowMapper<ActivityDTO> {
    @Override
    public ActivityDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ActivityDTO.builder()
                .id(rs.getLong("id"))
                .username(rs.getString("username"))
                .title(rs.getString("title"))
                .description(rs.getString("description"))
                .fee(rs.getBigDecimal("fee"))
                .createdDate(rs.getDate("createdDate").toLocalDate())
                .updatedDate(rs.getDate("updatedDate").toLocalDate())
                .build();
    }
}
