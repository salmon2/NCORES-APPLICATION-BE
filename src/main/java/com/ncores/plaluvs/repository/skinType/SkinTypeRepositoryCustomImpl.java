package com.ncores.plaluvs.repository.skinType;

import com.ncores.plaluvs.controller.skin.dto.QScoreData;
import com.ncores.plaluvs.controller.skin.dto.QSkinStatusRecordResponseDto;
import com.ncores.plaluvs.controller.skin.dto.ScoreData;
import com.ncores.plaluvs.controller.skin.dto.SkinStatusRecordResponseDto;
import com.ncores.plaluvs.domain.skintype.SkinType;
import com.ncores.plaluvs.domain.skintype.SkinTypeEnum;
import com.ncores.plaluvs.domain.user.User;
import com.ncores.plaluvs.exception.ErrorCode;
import com.ncores.plaluvs.exception.PlaluvsException;
import com.ncores.plaluvs.security.UserDetailsImpl;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    @Override
    public List<ScoreData> findSkinStatusBoumanCustom(UserDetailsImpl userDetails, LocalDateTime startDatetime, LocalDateTime endDatetime, SkinTypeEnum sort) throws PlaluvsException {
        LocalDateTime startDatetimeWeek = LocalDateTime.of(LocalDate.now().minusWeeks(1), LocalTime.of(0,0,0));
        LocalDateTime endDatetimeWeek = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));


        LocalDateTime startDatetimeMonth = LocalDateTime.of(LocalDate.now().minusWeeks(2), LocalTime.of(0,0,0)); //오늘 00:00:00
        LocalDateTime endDatetimeMonth = LocalDateTime.of(LocalDate.now().minusWeeks(1), LocalTime.of(23,59,59)); //오늘 23:59:59

        LocalDateTime startDatetimeWeekAgo = LocalDateTime.of(LocalDate.now().minusMonths(2), LocalTime.of(0,0,0)); //오늘 00:00:00
        LocalDateTime endDatetimeWeekAgo = LocalDateTime.of(LocalDate.now().minusWeeks(2), LocalTime.of(23,59,59)); //오늘 23:59:59



        List<ScoreData> result = queryFactory
                .select(
                        new QScoreData(
                                getTypeQuery(sort),
                                skinType.createdAt
                        )
                )
                .from(skinType)
                .where(skinType.user.eq(userDetails.getUser())
                        .and(getBetween(startDatetimeWeek, endDatetimeWeek)
                                .or(getBetween(startDatetimeMonth, endDatetimeMonth)
                                        .or(getBetween(startDatetimeWeekAgo, endDatetimeWeekAgo)))))
                .orderBy(getTypeQueryOrderBy(sort))
                .fetch();


        return result;
    }

    private BooleanExpression getBetween(LocalDateTime startDatetime, LocalDateTime endDatetime) {
        return skinType.createdAt.between(startDatetime, endDatetime);
    }

    private Expression<Long> getTypeQuery(SkinTypeEnum sort) throws PlaluvsException {
        if(sort.equals(SkinTypeEnum.DRY))
            return skinType.dryScore.avg().longValue();
        else if (sort.equals(SkinTypeEnum.OIL))
            return skinType.oilIndicateScore.avg().longValue();
        else if (sort.equals(SkinTypeEnum.SEN))
            return skinType.sensitivityScore.avg().longValue();
        else if (sort.equals(SkinTypeEnum.PIG))
            return skinType.pigmentScore.avg().longValue();
        else if (sort.equals(SkinTypeEnum.WIN))
            return skinType.winkleScore.avg().longValue();
        throw new PlaluvsException(ErrorCode.SKIN_TYPE_NOT_FOUND);
    }

    private OrderSpecifier<Double> getTypeQueryOrderBy(SkinTypeEnum sort) throws PlaluvsException {
        if(sort.equals(SkinTypeEnum.DRY))
            return skinType.dryScore.avg().asc();
        else if (sort.equals(SkinTypeEnum.OIL))
            return skinType.oilIndicateScore.avg().asc();
        else if (sort.equals(SkinTypeEnum.SEN))
            return skinType.sensitivityScore.avg().asc();
        else if (sort.equals(SkinTypeEnum.PIG))
            return skinType.pigmentScore.avg().asc();
        else if (sort.equals(SkinTypeEnum.WIN))
            return skinType.winkleScore.avg().asc();
        throw new PlaluvsException(ErrorCode.SKIN_TYPE_NOT_FOUND);
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
