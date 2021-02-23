package com.laonstory.ysu.global.error.exception;

public class FileDeleteException extends InvalidValueException{

    public FileDeleteException(String value) {
        super(value, ErrorCode.FILE_DELETE_ERROR);
    }
}
