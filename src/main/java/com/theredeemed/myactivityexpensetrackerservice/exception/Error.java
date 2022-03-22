package com.theredeemed.myactivityexpensetrackerservice.exception;

public enum Error {
    UNABLE_TO_SAVE_RECORD(1000, "Unable to save record"),
    RECORD_NOT_FOUND(1001, "Record Not Found"),
    UNABLE_TO_UPDATE_RECORD(1002, "Unable to update record"),
    UNABLE_TO_UPDATE_ACTIVITY_BALANCE(1003, "Unable to update activity balance"),
    INVALID_REQUEST_PAYLOAD(1004, "Invalid Request Payload");


    private final int code;
    private final String title;

    Error(int code, String title) {
        this.code = code;
        this.title = title;
    }

    public int getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return code + " - " + title;
    }
}
