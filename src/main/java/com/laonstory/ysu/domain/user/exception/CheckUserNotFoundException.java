package com.laonstory.ysu.domain.user.exception;

import com.laonstory.ysu.global.error.exception.ErrorCode;
import com.laonstory.ysu.global.error.exception.InvalidValueException;

public class CheckUserNotFoundException extends InvalidValueException {
    public CheckUserNotFoundException(String value) {
        super(value, ErrorCode.CHECK_USER_INVALID);
    }
}
