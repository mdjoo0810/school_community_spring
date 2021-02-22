package com.laonstory.ysu.domain.user.exception;

import com.laonstory.ysu.global.error.exception.ErrorCode;
import com.laonstory.ysu.global.error.exception.InvalidValueException;

public class StudentIdDuplicatedException extends InvalidValueException {
    public StudentIdDuplicatedException(final String value) {
        super(value, ErrorCode.STUDENT_ID_DUPLICATION);
    }
}