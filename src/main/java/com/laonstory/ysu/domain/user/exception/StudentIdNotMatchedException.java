package com.laonstory.ysu.domain.user.exception;

import com.laonstory.ysu.global.error.exception.ErrorCode;
import com.laonstory.ysu.global.error.exception.InvalidValueException;

public class StudentIdNotMatchedException extends InvalidValueException {

    public StudentIdNotMatchedException(final String value) {
        super(value, ErrorCode.STUDENT_ID_INPUT_INVALID);
    }
}
