package com.laonstory.ysu.domain.user.exception;

import com.laonstory.ysu.global.error.exception.ErrorCode;
import com.laonstory.ysu.global.error.exception.InvalidValueException;

public class AlreadyWithdrawUserException extends InvalidValueException {
    public AlreadyWithdrawUserException(String value) {
        super(value, ErrorCode.ALREADY_WITHDRAW_USER);
    }
}
