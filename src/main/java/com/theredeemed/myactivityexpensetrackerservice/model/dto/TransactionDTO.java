package com.theredeemed.myactivityexpensetrackerservice.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDTO {
    private Long id;
    private String activityName;
    private String actionCode;
    private BigDecimal amount;
    private BigDecimal balance;
    private LocalDateTime createdTimestamp;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionDTO)) return false;
        TransactionDTO that = (TransactionDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(activityName, that.activityName) &&
                Objects.equals(actionCode, that.actionCode) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(balance, that.balance) &&
                Objects.equals(createdTimestamp, that.createdTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, activityName, actionCode, amount, balance, createdTimestamp);
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "id=" + id +
                ", activityName=" + activityName +
                ", actionCode=" + actionCode +
                ", amount=" + amount +
                ", balance=" + balance +
                ", createdTimestamp=" + createdTimestamp +
                '}';
    }
}
