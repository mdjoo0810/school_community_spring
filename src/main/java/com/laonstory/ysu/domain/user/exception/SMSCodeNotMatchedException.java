package com.laonstory.ysu.domain.user.exception;

import com.laonstory.ysu.global.error.exception.ErrorCode;
import com.laonstory.ysu.global.error.exception.InvalidValueException;

public class SMSCodeNotMatchedException extends InvalidValueException {

    public SMSCodeNotMatchedException(String value) {
        super(value, ErrorCode.SMS_CODE_NOT_MATCHED);
    }
}
