package com.laonstory.ysu.global.error.exception;

public class FileSaveException extends InvalidValueException{
    public FileSaveException(String value) {
        super(value, ErrorCode.FILE_SAVE_ERROR);
    }
}
