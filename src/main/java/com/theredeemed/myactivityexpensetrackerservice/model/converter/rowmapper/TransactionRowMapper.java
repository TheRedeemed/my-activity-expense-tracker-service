package com.theredeemed.myactivityexpensetrackerservice.model.converter.rowmapper;

import com.theredeemed.myactivityexpensetrackerservice.model.dto.TransactionDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class TransactionRowMapper implements RowMapper<TransactionDTO> {
    @Override
    public TransactionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return TransactionDTO.builder()
                .id(rs.getLong("id"))
                .activityName(rs.getString("activityName"))
                .actionCode(rs.getString("actionCode"))
                .amount(rs.getBigDecimal("amount"))
                .balance(rs.getBigDecimal("balance"))
                .createdTimestamp(rs.getObject("createdTimestamp", LocalDateTime.class))
                .build();
    }
}
