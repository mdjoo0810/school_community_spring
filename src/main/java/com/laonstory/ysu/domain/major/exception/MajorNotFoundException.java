package com.laonstory.ysu.domain.major.exception;


import com.laonstory.ysu.global.error.exception.EntityNotFoundException;

public class MajorNotFoundException extends EntityNotFoundException {
    public MajorNotFoundException(Long target) {
        super(target + " is not found");
    }
}
