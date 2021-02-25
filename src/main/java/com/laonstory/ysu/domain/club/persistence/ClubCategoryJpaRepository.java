package com.laonstory.ysu.domain.club.persistence;

import com.laonstory.ysu.domain.club.domain.ClubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubCategoryJpaRepository extends JpaRepository<ClubCategory, Long> {

}
