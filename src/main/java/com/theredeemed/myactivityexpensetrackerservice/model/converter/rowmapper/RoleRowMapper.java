package com.theredeemed.myactivityexpensetrackerservice.model.converter.rowmapper;

import com.theredeemed.myactivityexpensetrackerservice.model.dto.RoleDTO;
import com.theredeemed.myactivityexpensetrackerservice.model.dto.Roles;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RoleRowMapper implements RowMapper<RoleDTO> {
    @Override
    public RoleDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return RoleDTO.builder()
                .id(rs.getLong("id"))
                .roleName(Roles.valueOf(rs.getString("roleName")))
                .description(rs.getString("description"))
                .createdBy(rs.getString("createdBy"))
                .createdDate(rs.getDate("createdDate").toLocalDate())
                .updatedBy(rs.getString("updatedBy"))
                .updatedDate(rs.getDate("updatedDate").toLocalDate())
                .build();
    }
}
