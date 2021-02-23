package com.laonstory.ysu.domain.user.exception;

import com.laonstory.ysu.global.error.exception.ErrorCode;
import com.laonstory.ysu.global.error.exception.InvalidValueException;

public class PhoneDuplicateException extends InvalidValueException {
    public PhoneDuplicateException(String value) {
        super(value, ErrorCode.PHONE_DUPLICATION);
    }
}
