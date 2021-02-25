package com.laonstory.ysu.domain.organization.exception;

import com.laonstory.ysu.global.error.exception.EntityNotFoundException;

public class OgzNoticeCommentNotFoundException extends EntityNotFoundException {

    public OgzNoticeCommentNotFoundException(Long id) {
        super(id + " is not found.");
    }
}
