package com.laonstory.ysu.domain.college.exception;

import com.laonstory.ysu.global.error.exception.EntityNotFoundException;

public class CollegeNotFoundException extends EntityNotFoundException {
    public CollegeNotFoundException(Long target) {
        super(target + " is not found");
    }
}
