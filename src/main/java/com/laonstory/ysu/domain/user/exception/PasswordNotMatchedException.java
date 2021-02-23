package com.laonstory.ysu.domain.user.exception;

import com.laonstory.ysu.global.error.exception.ErrorCode;
import com.laonstory.ysu.global.error.exception.InvalidValueException;

public class PasswordNotMatchedException extends InvalidValueException {
    public PasswordNotMatchedException(final String value) {
        super(value, ErrorCode.PASSWORD_INPUT_INVALID);
    }
}
