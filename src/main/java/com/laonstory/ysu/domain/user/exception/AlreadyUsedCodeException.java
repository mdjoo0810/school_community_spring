package com.laonstory.ysu.domain.user.exception;

import com.laonstory.ysu.global.error.exception.ErrorCode;
import com.laonstory.ysu.global.error.exception.InvalidValueException;

public class AlreadyUsedCodeException extends InvalidValueException {
    public AlreadyUsedCodeException(String value) {
        super(value, ErrorCode.ALREADY_USED_CODE);
    }
}
