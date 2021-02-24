package com.laonstory.ysu.domain.faq.exception;

import com.laonstory.ysu.global.error.exception.ErrorCode;
import com.laonstory.ysu.global.error.exception.InvalidValueException;

public class FaqNotFoundException extends InvalidValueException {
    public FaqNotFoundException(String value) {
        super(value, ErrorCode.FAQ_NOT_FOUND);
    }
}
