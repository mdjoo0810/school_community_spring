package com.laonstory.ysu.domain.organization.application;

import com.laonstory.ysu.domain.organization.domain.OgzNotice;
import com.laonstory.ysu.domain.organization.domain.OgzNoticeLike;
import com.laonstory.ysu.domain.organization.persistence.OgzNoticeLikeJpaRepository;
import com.laonstory.ysu.domain.organization.persistence.OgzNoticeLikeRepositorySupport;
import com.laonstory.ysu.domain.organization.persistence.OgzNoticeRepositorySupport;
import com.laonstory.ysu.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class OgzNoticeLikeService {

    private final OgzNoticeRepositorySupport ogzNoticeRepositorySupport;
    private final OgzNoticeLikeJpaRepository ogzNoticeLikeJpaRepository;
    private final OgzNoticeLikeRepositorySupport ogzNoticeLikeRepositorySupport;

    public String pushLike (Long noticeId, User user) {
        OgzNotice notice = ogzNoticeRepositorySupport.findById(noticeId);

        for (OgzNoticeLike like : notice.getLikes()) {
            if (like.getUser().getId().equals(user.getId())) {
                notice.cancelLike(like);
                return "CANCELED";
            }
        }

        OgzNoticeLike like = OgzNoticeLike.create(notice, user);

        notice.addLike(like, user);
        return "ADDED";
    }

}
