package com.theredeemed.myactivityexpensetrackerservice.exception;

/**
 * This ActivityException wraps all exceptions in the application and enriches them with a custom error code and message
 *
 * @author Abdoul Kader Soumahoro
 */
public class ActivityException extends Exception {
    private static final long serialVersionUID = 1168874930778540232L;

    private final Error error;
    private String errorDescription;

    public ActivityException(final Error error) {
        super(error.toString());
        this.error = error;
    }

    public ActivityException(final Error error, String errorDescription) {
        this.error = error;
        this.errorDescription = errorDescription;
    }

    public ActivityException(final Error error, Throwable cause, boolean enableSuppression,
                             boolean writableStackTrace) {
        super(error.toString(), cause, enableSuppression, writableStackTrace);
        this.error = error;
    }

    public ActivityException(final Error error, String errorDescription, Throwable cause, boolean enableSuppression,
                             boolean writableStackTrace) {
        super(error.toString(), cause, enableSuppression, writableStackTrace);
        this.error = error;
        this.errorDescription = errorDescription;
    }

    public ActivityException(final Error error, Throwable cause) {
        super(error.toString(), cause);
        this.error = error;
    }

    public ActivityException(final Error error, String errorDescription, Throwable cause) {
        super(error.toString(), cause);
        this.error = error;
        this.errorDescription = errorDescription;
    }

    public Error getError() {
        return this.error;
    }

    public String getErrorDescription() {
        return this.errorDescription;
    }
}
