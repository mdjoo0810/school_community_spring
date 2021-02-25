package com.laonstory.ysu.domain.organization.exception;

import com.laonstory.ysu.global.error.exception.EntityNotFoundException;

public class OgzNoticeNotFoundException extends EntityNotFoundException {

    public OgzNoticeNotFoundException(Long id) {
        super(id + " is not found.");
    }
}
