package com.laonstory.ysu.domain.association.persistence;

import com.laonstory.ysu.domain.association.domain.Association;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociationJpaRepository extends JpaRepository<Association, Long> {
}
