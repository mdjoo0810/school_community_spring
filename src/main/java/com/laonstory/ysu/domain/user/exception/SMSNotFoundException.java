package com.laonstory.ysu.domain.user.exception;

import com.laonstory.ysu.global.error.exception.ErrorCode;
import com.laonstory.ysu.global.error.exception.InvalidValueException;

public class SMSNotFoundException extends InvalidValueException {
    public SMSNotFoundException(String value) {
        super(value, ErrorCode.SMS_NOT_FOUND);
    }
}
