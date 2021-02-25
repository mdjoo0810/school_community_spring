package com.laonstory.ysu.domain.club.persistence;

import com.laonstory.ysu.domain.club.domain.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubJpaRepository extends JpaRepository<Club, Long> {
}
