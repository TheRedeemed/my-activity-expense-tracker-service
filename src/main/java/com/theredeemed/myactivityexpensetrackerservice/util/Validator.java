package com.theredeemed.myactivityexpensetrackerservice.util;

import com.theredeemed.myactivityexpensetrackerservice.exception.ActivityException;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

import static com.theredeemed.myactivityexpensetrackerservice.exception.Error.INVALID_REQUEST_PAYLOAD;

public class Validator {
    private Validator() {
    }

    public static void validateRequestPayload(Object o) throws ActivityException {
        if(o == null)
            throw new ActivityException(INVALID_REQUEST_PAYLOAD, "A non null request object is required to complete " +
                    "the request");
    }

    public static void validateUpdateRequestPayload(final Map<String, String> updateRequestPayload,
                                                    final String entityName) throws ActivityException {
        validateRequestPayload(updateRequestPayload);
        final String FIELD_NAME = " ${fieldName}";
        String errorDescription = String.format("%s %s is required to complete the request", entityName, FIELD_NAME);
        if (StringUtils.isBlank(updateRequestPayload.get("id")))
            throw new ActivityException(INVALID_REQUEST_PAYLOAD, errorDescription.replace(FIELD_NAME, "ID"));

        if (StringUtils.isBlank(updateRequestPayload.get("description")))
            throw new ActivityException(INVALID_REQUEST_PAYLOAD,
                    errorDescription.replace(FIELD_NAME, "Description"));
    }
}
