package com.laonstory.ysu.domain.organization.persistence;

import com.laonstory.ysu.domain.organization.domain.OgzNotice;
import com.laonstory.ysu.domain.organization.exception.OgzNoticeNotFoundException;
import com.laonstory.ysu.domain.organization.model.OgzNoticeMenu;
import com.laonstory.ysu.domain.organization.model.OgzNoticeSearchModel;
import com.laonstory.ysu.domain.organization.model.OgzTabType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;

import static com.laonstory.ysu.domain.organization.domain.QOgzNotice.ogzNotice;

@Repository
public class OgzNoticeRepositorySupport extends QuerydslRepositorySupport {

    private JPAQueryFactory queryFactory;

    public OgzNoticeRepositorySupport(JPAQueryFactory queryFactory) {
        super(OgzNotice.class);
        this.queryFactory = queryFactory;
    }

    public OgzNotice findById(Long noticeId){
        OgzNotice notice = queryFactory
                .selectFrom(ogzNotice)
                .where(ogzNotice.id.eq(noticeId))
                .fetchOne();

        if (notice == null) throw new OgzNoticeNotFoundException(noticeId);

        return notice;
    }

    // 총학생회, 단과대, 학부학과, 학생단체 관련
    public List<OgzNotice> findLimit_3ByOgzNoticeSearchModel(OgzNoticeSearchModel model) {
        return queryFactory
                .selectFrom(ogzNotice)
                .where(eqTabType(model.getTabType()),
                        eqMenu(model.getMenus()),
                        eqOgzId(model.getOgzId()))
                .leftJoin(ogzNotice.organization)
                .limit(3)
                .orderBy(ogzNotice.isFix.asc(), ogzNotice.createdDate.desc())
                .fetch();
    }

    // 학회 및 동아리 관련
    public List<OgzNotice> findLimit_3ByOgzNoticeSearchModelAndIsWholeTrue(OgzNoticeSearchModel model) {
        return queryFactory
                .selectFrom(ogzNotice)
                .where(eqTabType(model.getTabType()),
                        eqMenu(model.getMenus()),
                        eqOgzId(model.getOgzId()),
                        ogzNotice.isWhole.isTrue())
                .leftJoin(ogzNotice.organization)
                .limit(3)
                .orderBy(ogzNotice.isFix.asc(), ogzNotice.createdDate.desc())
                .fetch();
    }

    // 전체 리스트 불러오기 (페이징)
    public Page<OgzNotice> search(OgzNoticeSearchModel model, Pageable pageable) {
        QueryResults<OgzNotice> results = queryFactory
                .selectFrom(ogzNotice)
                .where(eqTabType(model.getTabType()),
                        eqMenu(model.getMenus()),
                        isWhole(model.getIsWhole()),
                        eqOgzId(model.getOgzId()),
                        eqYear(model.getYear()),
                        containsQuery(model.getQuery()))
                .leftJoin(ogzNotice.organization)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(ogzNotice.isFix.asc(), ogzNotice.createdDate.desc())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    // ==================================================
    // 조건
    // ==================================================
    private BooleanExpression eqTabType(OgzTabType type) {
        if (type == null) {
            return null;
        }

        return ogzNotice.tabType.eq(type);
    }

    private BooleanExpression eqMenu(List<OgzNoticeMenu> menus) {
        if(menus.size() == 0) {
            return null;
        }

        return ogzNotice.menu.in(menus);
    }

    private BooleanExpression eqOgzId(Long ogzId) {
        if (ogzId == null) {
            return null;
        }

        return ogzNotice.organization.id.eq(ogzId);
    }

    private BooleanExpression isWhole(Boolean isWhole) {
        if (isWhole == null || !isWhole) {
            return ogzNotice.isWhole.isFalse();
        }

        return ogzNotice.isWhole.isTrue();
    }

    private BooleanExpression containsQuery(String query) {
        if (query == null) {
            return null;
        }

        if (query.isBlank()) {
            return null;
        }

        return ogzNotice.title.contains(query)
                .or(ogzNotice.content.contains(query))
                .or(ogzNotice.tags.any().name.contains(query));
    }

    private BooleanExpression eqYear(String year) {
        if (year == null) {
            return null;
        }

        if (year.isBlank()) {
            return null;
        }

        LocalDateTime startDate = Year.parse(year).atMonth(1).atDay(1).atStartOfDay();
        LocalDateTime endDate = Year.parse(Integer.toString((Integer.parseInt(year) + 1))).atMonth(1).atDay(31).atStartOfDay();

        return ogzNotice.createdDate.between(startDate, endDate);
    }
}
