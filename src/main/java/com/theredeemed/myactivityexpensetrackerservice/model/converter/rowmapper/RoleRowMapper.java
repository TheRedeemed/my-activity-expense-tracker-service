package com.theredeemed.myactivityexpensetrackerservice.model.converter.rowmapper;

import com.theredeemed.myactivityexpensetrackerservice.model.dto.RoleDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRowMapper implements RowMapper<RoleDTO> {
    @Override
    public RoleDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return RoleDTO.builder()
                .id(rs.getLong("id"))
                .roleName(rs.getString("roleName"))
                .description(rs.getString("description"))
                .createdBy(rs.getString("createdBy"))
                .createdDate(rs.getDate("createdDate"))
                .updatedBy(rs.getString("updatedBy"))
                .updatedDate(rs.getDate("updatedDate"))
                .build();
    }
}
