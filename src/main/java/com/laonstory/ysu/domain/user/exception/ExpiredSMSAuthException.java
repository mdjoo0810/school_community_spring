package com.laonstory.ysu.domain.user.exception;

import com.laonstory.ysu.global.error.exception.ErrorCode;
import com.laonstory.ysu.global.error.exception.InvalidValueException;

public class ExpiredSMSAuthException extends InvalidValueException {
    public ExpiredSMSAuthException(String value) {
        super(value, ErrorCode.EXPIRED_CODE);
    }
}
