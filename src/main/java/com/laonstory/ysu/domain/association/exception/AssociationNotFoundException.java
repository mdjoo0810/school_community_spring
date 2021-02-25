package com.laonstory.ysu.domain.association.exception;

import com.laonstory.ysu.global.error.exception.EntityNotFoundException;

public class AssociationNotFoundException extends EntityNotFoundException {
    public AssociationNotFoundException(Long associationId) {
        super(associationId + "is not found.");
    }
}
