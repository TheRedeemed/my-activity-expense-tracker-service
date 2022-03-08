package com.theredeemed.myactivityexpensetrackerservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ErrorMessage {
    private Integer errorCode;
    public String errorTitle;
    private String errorDescription;
    private LocalDateTime timestamp;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErrorMessage)) return false;
        ErrorMessage that = (ErrorMessage) o;
        return Objects.equals(errorCode, that.errorCode) &&
                Objects.equals(errorTitle, that.errorTitle) &&
                Objects.equals(errorDescription, that.errorDescription) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorCode, errorTitle, errorDescription, timestamp);
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "errorCode='" + errorCode + '\'' +
                ", errorTitle='" + errorTitle + '\'' +
                ", errorDescription='" + errorDescription + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
