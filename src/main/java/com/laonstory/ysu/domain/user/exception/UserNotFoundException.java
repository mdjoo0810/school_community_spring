package com.laonstory.ysu.domain.user.exception;

import com.laonstory.ysu.global.error.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException(Long target) {
        super(target + "is not found");
    }
}
