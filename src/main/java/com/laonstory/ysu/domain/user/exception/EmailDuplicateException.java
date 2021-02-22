package com.laonstory.ysu.domain.user.exception;

import com.laonstory.ysu.global.error.exception.ErrorCode;
import com.laonstory.ysu.global.error.exception.InvalidValueException;

public class EmailDuplicateException extends InvalidValueException {
    public EmailDuplicateException(final String email) {
        super(email, ErrorCode.EMAIL_DUPLICATION);
    }
}
