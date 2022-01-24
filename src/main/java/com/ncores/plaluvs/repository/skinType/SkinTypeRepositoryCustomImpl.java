package com.ncores.plaluvs.repository.skinType;

import com.ncores.plaluvs.controller.skin.dto.QSkinStatusRecordResponseDto;
import com.ncores.plaluvs.controller.skin.dto.SkinStatusRecordResponseDto;
import com.ncores.plaluvs.domain.skintype.QSkinType;
import com.ncores.plaluvs.domain.skintype.SkinType;
import com.ncores.plaluvs.domain.user.User;
import com.ncores.plaluvs.security.UserDetailsImpl;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import static com.ncores.plaluvs.domain.skintype.QSkinType.skinType;

@Repository
@RequiredArgsConstructor
public class SkinTypeRepositoryCustomImpl implements SkinTypeRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<SkinType> findAllCustom(UserDetailsImpl userDetails, PageRequest pageRequest, String sort) {
        List<SkinType> result = queryFactory
                .selectFrom(skinType)
                .where(skinType.user.eq(userDetails.getUser()))
                .limit(pageRequest.getPageSize())
                .offset(pageRequest.getOffset())
                .orderBy(getSortSkinTypeByCreatedAt(sort))
                .fetch();

        JPAQuery<SkinType> countQuery = countSkinTypeByUser(userDetails.getUser());


        return PageableExecutionUtils.getPage(result, pageRequest, countQuery::fetchCount);
    }

    @Override
    public Double findAverageCustom(UserDetailsImpl userDetails) {
        List<Tuple> fetch = queryFactory
                .select(
                        skinType.score.avg(),
                        skinType.score.sum()
                )
                .from(skinType)
                .where(skinType.user.eq(userDetails.getUser()))
                .fetch();
        Double average = fetch.get(0).get(skinType.score.avg());

        return average;
    }

    @Override
    public Page<SkinStatusRecordResponseDto> findskinStatusRecord(UserDetailsImpl userDetails, PageRequest pageRequest) {
        List<SkinStatusRecordResponseDto> result = queryFactory
                .select(
                        new QSkinStatusRecordResponseDto(
                                skinType.createdAt.stringValue(),
                                skinType.score.stringValue()
                        )
                ).from(skinType)
                .where(skinType.user.eq(userDetails.getUser()))
                .limit(pageRequest.getPageSize())
                .offset(pageRequest.getOffset())
                .orderBy(skinType.createdAt.desc())
                .fetch();

        JPAQuery<SkinType> countQuery = countfindskinStatusRecord(userDetails.getUser());

        return PageableExecutionUtils.getPage(result, pageRequest, countQuery::fetchCount);
    }

    private JPAQuery<SkinType> countfindskinStatusRecord(User user) {
        return queryFactory
                .selectFrom(skinType)
                .where(skinType.user.eq(user));
    }


    private JPAQuery<SkinType> countSkinTypeByUser(User user) {
        return queryFactory
                .selectFrom(skinType)
                .where(skinType.user.eq(user));
    }

    private OrderSpecifier<? extends Serializable> getSortSkinTypeByCreatedAt(String sort) {
        if(sort.equals("asc"))
            return skinType.createdAt.asc();
        else if(sort.equals("desc"))
            return skinType.createdAt.desc();
        return null;
    }
}
