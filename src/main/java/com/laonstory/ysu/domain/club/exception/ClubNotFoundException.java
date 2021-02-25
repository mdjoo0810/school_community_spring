package com.laonstory.ysu.domain.club.exception;

import com.laonstory.ysu.global.error.exception.EntityNotFoundException;

public class ClubNotFoundException extends EntityNotFoundException {
    public ClubNotFoundException(Long clubId) {
        super(clubId + " is not found.");
    }
}
