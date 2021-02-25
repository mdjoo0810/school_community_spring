package com.laonstory.ysu.global.error.exception;

public class NotMyEntityException extends InvalidValueException{
    public NotMyEntityException(String value) {
        super(value, ErrorCode.NOT_MY_ENTITY);
    }
}
