package com.theredeemed.myactivityexpensetrackerservice.exception;

public enum Error {
    UNABLE_TO_SAVE_RECORD(0, "Unable to save record"),
    RECORD_NOT_FOUND(1, "Record Not Found"),
    UNABLE_TO_UPDATE_RECORD(2, "Record Not Found"),
    UNABLE_TO_UPDATE_ACTIVITY_BALANCE(3, "Unable to update activity balance"),
    INVALID_REQUEST_PAYLOAD(4, "Invalid Request Payload");


    private final int code;
    private final String description;

    Error(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return code + " - " + description;
    }
}
