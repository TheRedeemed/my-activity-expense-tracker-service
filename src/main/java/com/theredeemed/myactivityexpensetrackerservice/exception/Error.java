package com.theredeemed.myactivityexpensetrackerservice.exception;

public enum Error {
    UNABLE_TO_SAVE_ACTIVITY(0, "Unable to save activity"),
    ACTIVITY_NOT_FOUND(1, "Activity Not Found");

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
