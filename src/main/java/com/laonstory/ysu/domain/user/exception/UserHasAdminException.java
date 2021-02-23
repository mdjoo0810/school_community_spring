package com.laonstory.ysu.domain.user.exception;

import com.laonstory.ysu.global.error.exception.ErrorCode;
import com.laonstory.ysu.global.error.exception.InvalidValueException;

public class UserHasAdminException extends InvalidValueException {
    public UserHasAdminException(String value) {
        super(value, ErrorCode.USER_HAS_ADMIN_ROLE);
    }
}
