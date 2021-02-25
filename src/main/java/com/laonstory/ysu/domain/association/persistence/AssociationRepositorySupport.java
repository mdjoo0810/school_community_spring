package com.laonstory.ysu.domain.association.persistence;

import com.laonstory.ysu.domain.association.domain.Association;
import com.laonstory.ysu.domain.association.exception.AssociationNotFoundException;
import com.laonstory.ysu.domain.association.model.AssociationSearchModel;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.laonstory.ysu.domain.association.domain.QAssociation.association;
import static com.laonstory.ysu.domain.association.domain.QAssociationTag.associationTag;

@Repository
public class AssociationRepositorySupport extends QuerydslRepositorySupport {

    private JPAQueryFactory queryFactory;

    public AssociationRepositorySupport(JPAQueryFactory queryFactory) {
        super(Association.class);
        this.queryFactory = queryFactory;
    }

    public Association findById(Long associationId) {
        Association result = queryFactory
                .selectFrom(association)
                .where(association.id.eq(associationId))
                .fetchOne();

        if (result == null) throw new AssociationNotFoundException(associationId);

        return result;
    }

    public List<Association> findLimit_3BySearchModelOrderByIdDesc(AssociationSearchModel model) {
        return queryFactory
                .selectFrom(association)
                .where(eqCollege(model.getCollegeId()),
                        eqMajor(model.getMajorId()))
                .orderBy(association.id.desc())
                .limit(3)
                .fetch();
    }

    public Page<Association> findBySearchModelWithPaging(AssociationSearchModel model, Pageable pageable) {
        QueryResults<Association> results = queryFactory
                .selectFrom(association)
                .where(eqCollege(model.getCollegeId()),
                        eqMajor(model.getMajorId()),
                        eqQuery(model.getQuery()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(association.id.desc())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    // ========================================
    // 조건
    // ========================================
    private BooleanExpression eqQuery(String query) {
        if (query.isEmpty() || query.isBlank()) {
            return null;
        }

        return association.title.contains(query)
                .or(association.description.contains(query))
                .or(associationTag.name.contains(query));
    }

    private BooleanExpression eqCollege(Long collegeId) {
        if (collegeId == null) {
            return null;
        }

        return association.major.college.id.eq(collegeId);
    }

    private BooleanExpression eqMajor(Long majorId) {
        if (majorId == null) {
            return null;
        }

        return association.major.id.eq(majorId);
    }

}
