package com.laonstory.ysu.domain.organization.exception;

import com.laonstory.ysu.global.error.exception.InvalidValueException;

public class OgzNoticeLikeNotFoundException extends InvalidValueException {

    public OgzNoticeLikeNotFoundException(Long id) {
        super(id + " is not found.");
    }
}
