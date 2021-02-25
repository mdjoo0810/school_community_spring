package com.laonstory.ysu.domain.club.application;

import com.laonstory.ysu.domain.club.domain.ClubCategory;
import com.laonstory.ysu.domain.club.dto.ClubCategoryResponse;
import com.laonstory.ysu.domain.club.persistence.ClubCategoryRepositorySupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClubCategorySearchService {

    private final ClubCategoryRepositorySupport clubCategoryRepositorySupport;

    public List<ClubCategoryResponse> findAllByIsAssociationIsFalse() {
        List<ClubCategory> list = clubCategoryRepositorySupport.findAllByIsAssociationIsFalse();

        return list.stream().map(ClubCategoryResponse::new).collect(Collectors.toList());
    }

}
