package com.laonstory.ysu.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(String message) {
        super(message, ErrorCode.ENTITY_NOT_FOUND);
    }
}