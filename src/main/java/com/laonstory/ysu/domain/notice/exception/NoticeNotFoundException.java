package com.laonstory.ysu.domain.notice.exception;

import com.laonstory.ysu.global.error.exception.ErrorCode;
import com.laonstory.ysu.global.error.exception.InvalidValueException;

public class NoticeNotFoundException extends InvalidValueException {
    public NoticeNotFoundException(String value) {
        super(value, ErrorCode.NOTICE_NOT_FOUND);
    }
}
