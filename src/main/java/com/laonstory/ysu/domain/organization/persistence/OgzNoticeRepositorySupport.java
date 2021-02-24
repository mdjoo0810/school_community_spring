package com.laonstory.ysu.domain.organization.persistence;

import com.laonstory.ysu.domain.organization.domain.OgzNotice;
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

import java.util.List;

import static com.laonstory.ysu.domain.organization.domain.QOgzNotice.ogzNotice;

@Repository
public class OgzNoticeRepositorySupport extends QuerydslRepositorySupport {

    private JPAQueryFactory queryFactory;

    public OgzNoticeRepositorySupport(JPAQueryFactory queryFactory) {
        super(OgzNotice.class);
        this.queryFactory = queryFactory;
    }

    public List<OgzNotice> findLimit_3ByOgzNoticeSearchModel(OgzNoticeSearchModel model) {
        return queryFactory
                .selectFrom(ogzNotice)
                .where(eqTabType(model.getTabType()),
                        eqMenu(model.getMenus()),
                        eqOgzId(model.getOgzId()))
                .limit(3)
                .orderBy(ogzNotice.isFix.asc(), ogzNotice.createdDate.desc())
                .fetch();
    }

    public List<OgzNotice> findLimit_3ByOgzNoticeSearchModelAndIsWholeTrue(OgzNoticeSearchModel model) {
        return queryFactory
                .selectFrom(ogzNotice)
                .where(eqTabType(model.getTabType()),
                        eqMenu(model.getMenus()),
                        eqOgzId(model.getOgzId()),
                        ogzNotice.isWhole.isTrue())
                .limit(3)
                .orderBy(ogzNotice.isFix.asc(), ogzNotice.createdDate.desc())
                .fetch();
    }

    public Page<OgzNotice> findAllNoticeByTabType(OgzTabType type, Pageable pageable) {
        QueryResults<OgzNotice> results = queryFactory
                .selectFrom(ogzNotice)
                .where(ogzNotice.tabType.eq(type),
                        ogzNotice.isWhole.isTrue(),
                        ogzNotice.menu.eq(OgzNoticeMenu.NOTICE))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(ogzNotice.isFix.asc(), ogzNotice.createdDate.desc())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

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
}
