package com.ncores.plaluvs.repository.cosmetic;


import com.ncores.plaluvs.controller.cometic.dto.DetailCosmeticDto;
import com.ncores.plaluvs.controller.cometic.dto.QDetailCosmeticDto;
import com.ncores.plaluvs.controller.cometic.dto.QSimpleCosmeticDto;
import com.ncores.plaluvs.controller.cometic.dto.SimpleCosmeticDto;
import com.ncores.plaluvs.controller.user.dto.CosmeticDto;
import com.ncores.plaluvs.controller.user.dto.QCosmeticDto;
import com.ncores.plaluvs.domain.*;
import com.ncores.plaluvs.domain.user.QUser;
import com.ncores.plaluvs.domain.user.User;
import com.ncores.plaluvs.security.UserDetailsImpl;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

import static com.ncores.plaluvs.domain.QCategory.category;
import static com.ncores.plaluvs.domain.QCosmetic.cosmetic;
import static com.ncores.plaluvs.domain.QCosmeticElements.cosmeticElements;
import static com.ncores.plaluvs.domain.QElements.elements;
import static com.ncores.plaluvs.domain.QUserCosmetic.userCosmetic;

@Repository
@RequiredArgsConstructor
public class CosmeticRepositoryCustomImpl implements CosmeticRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<SimpleCosmeticDto> findCosmeticWorry(List<Elements> findElements, UserDetailsImpl userDetails) {
        List<SimpleCosmeticDto> result = queryFactory
                .select(
                        new QSimpleCosmeticDto(
                                cosmeticElements.cosmetic.id,
                                cosmeticElements.cosmetic.itemImg,
                                cosmeticElements.cosmetic.itemName,
                                (userDetails == null) ? setFalse() : distinguishBookmarkExistUser(cosmeticElements.cosmetic, userDetails.getUser()),
                                cosmeticElements.cosmetic.itemBrand,
                                cosmeticElements.cosmetic.category.id
                        )
                )
                .from(cosmeticElements)
                .join(cosmeticElements.elements, elements)
                .where(cosmeticElements.elements.in(findElements))
                .limit(7)
                .fetch();

        return result;
    }

    @Override
    public List<SimpleCosmeticDto> findAllbyBouman(UserDetailsImpl userDetails, List<Elements> findElements) {
        List<SimpleCosmeticDto> result = queryFactory
                .select(
                        new QSimpleCosmeticDto(
                                cosmeticElements.cosmetic.id,
                                cosmeticElements.cosmetic.itemImg,
                                cosmeticElements.cosmetic.itemName,
                                (userDetails == null) ? setFalse() : distinguishBookmarkExistUser(cosmeticElements.cosmetic, userDetails.getUser()),
                                cosmeticElements.cosmetic.itemBrand,
                                cosmeticElements.cosmetic.category.id
                        )
                )
                .from(cosmeticElements)
                .join(cosmeticElements.elements, elements)
                .where(cosmeticElements.elements.in(findElements))
                .limit(7)
                .fetch();

        return result;
    }

    @Override
    public Page<DetailCosmeticDto> findAllByCategoryAndBouman(UserDetailsImpl userDetails, List<Elements> findElements,
                                                              Category findCategory, PageRequest pageRequest, String sort) {
        List<DetailCosmeticDto> result = queryFactory
                .select(
                        new QDetailCosmeticDto(
                               cosmeticElements.cosmetic.id,
                                cosmeticElements.cosmetic.itemImg,
                                cosmeticElements.cosmetic.itemBrand,
                                cosmeticElements.cosmetic.itemName,
                                cosmeticElements.cosmetic.price,
                                (userDetails == null) ? setFalse() : distinguishBookmarkExistUser(cosmeticElements.cosmetic, userDetails.getUser()),
                                cosmeticElements.cosmetic.category.id
                        )
                )
                .from(cosmeticElements)
                .distinct()
                .join(cosmeticElements.elements, elements)
                .where(cosmeticElements.elements.in(findElements).and(cosmeticElements.cosmetic.category.eq(findCategory)))
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .orderBy(getSortCosmeticElements(sort))
                .fetch();

        JPAQuery<CosmeticElements> countQuery = countDetailCosmeticByElementsList(findElements, findCategory);

        return PageableExecutionUtils.getPage(result, pageRequest, countQuery::fetchCount);
    }


    private JPAQuery<CosmeticElements> countDetailCosmeticByElementsList(List<Elements> findElements, Category findCategory) {
        return queryFactory
                .selectFrom(cosmeticElements)
                .join(cosmeticElements.elements, elements)
                .where(cosmeticElements.elements.in(findElements).and(cosmeticElements.cosmetic.category.eq(findCategory)));

    }
    @Override
    public Page<DetailCosmeticDto> findAllByCategoryCustom(UserDetailsImpl userDetails, Category findCategory, PageRequest pageRequest, String sort) {
        List<DetailCosmeticDto> result = queryFactory
                .select(
                        new QDetailCosmeticDto(
                                cosmetic.id,
                                cosmetic.itemImg,
                                cosmetic.itemBrand,
                                cosmetic.itemName,
                                cosmetic.price,
                                (userDetails == null) ? setFalse() : distinguishBookmarkExistUser(cosmeticElements.cosmetic, userDetails.getUser()),
                                cosmetic.category.id
                        )
                )
                .from(cosmetic)
                .distinct()
                .join(cosmetic.category, category)
                .where(category.id.eq(findCategory.getId()))
                .limit(pageRequest.getPageSize())
                .offset(pageRequest.getOffset())
                .orderBy(getSortCosmetic(sort))
                .fetch();

        JPAQuery<Cosmetic> countQuery = countDetailCosmetic(findCategory);

        return PageableExecutionUtils.getPage(result, pageRequest, countQuery::fetchCount);
    }

    private JPAQuery<Cosmetic> countDetailCosmetic(Category findCategory) {
        return queryFactory
                .selectFrom(cosmetic)
                .join(cosmetic.category, category)
                .where(category.id.eq(findCategory.getId()));
    }

    @Override
    public Page<DetailCosmeticDto> findCosmeticByElementsCustom(UserDetailsImpl userDetails, Elements findElements, Category findCategory, PageRequest pageRequest, String sort) {
        List<DetailCosmeticDto> result = queryFactory
                .select(
                        new QDetailCosmeticDto(
                                cosmeticElements.cosmetic.id,
                                cosmeticElements.cosmetic.itemImg,
                                cosmeticElements.cosmetic.itemBrand,
                                cosmeticElements.cosmetic.itemName,
                                cosmeticElements.cosmetic.price,
                                (userDetails == null) ? setFalse() : distinguishBookmarkExistUser(cosmeticElements.cosmetic, userDetails.getUser()),
                                cosmeticElements.cosmetic.category.id
                        )
                )
                .from(cosmeticElements)
                .join(cosmeticElements.elements)
                .where(cosmeticElements.elements.eq(findElements).and(cosmeticElements.cosmetic.category.eq(findCategory)))
                .limit(pageRequest.getPageSize())
                .offset(pageRequest.getOffset())
                .orderBy(getSortCosmeticElements(sort))
                .fetch();

        JPAQuery<CosmeticElements> countQuery = countDetailCosmeticByElement(findElements, findCategory);

        return PageableExecutionUtils.getPage(result, pageRequest, countQuery::fetchCount);
    }

    @Override
    public List<CosmeticDto> findAllByUserCustom(User user) {
        List<CosmeticDto> result = queryFactory
                .select(
                        new QCosmeticDto(
                            userCosmetic.cosmetic.id,
                            userCosmetic.cosmetic.itemName,
                            userCosmetic.cosmetic.itemImg,
                            (user == null) ? setFalse(): distinguishBookmarkExistUser(userCosmetic.cosmetic, user),
                            userCosmetic.cosmetic.category.id,
                            userCosmetic.cosmetic.itemBrand
                        )
                )
                .from(userCosmetic)
                .join(userCosmetic.cosmetic, cosmetic)
                .join(userCosmetic.user, QUser.user)
                .where(QUser.user.id.eq(user.getId()).and(userCosmetic.cosmetic.id.eq(cosmetic.id)))
                .fetch();


        return result;
    }

    @Override
    public List<SimpleCosmeticDto> findCosmeticNoneWorry(User user) {
        return queryFactory
                .select(
                        new QSimpleCosmeticDto(
                                cosmetic.id,
                                cosmetic.itemImg,
                                cosmetic.itemName,
                                (user == null) ? setFalse() : distinguishBookmarkExistUser(cosmeticElements.cosmetic, user),
                                cosmetic.itemBrand,
                                cosmetic.category.id
                        )
                )
                .from(cosmetic)
                .limit(7)
                .fetch();

    }



    private JPAQuery<UserCosmetic> countMyBookmarkUserCosmetic(User user) {
        return queryFactory
                .selectFrom(userCosmetic)
                .join(userCosmetic.cosmetic, cosmetic)
                .join(userCosmetic.user, QUser.user)
                .where(QUser.user.id.eq(user.getId()));
    }

    private JPAQuery<CosmeticElements> countDetailCosmeticByElement(Elements findElements, Category findCategory) {
        JPAQuery<CosmeticElements> where = queryFactory
                .select(cosmeticElements)
                .from(cosmeticElements)
                .join(cosmeticElements.elements)
                .where(cosmeticElements.elements.eq(findElements).and(cosmeticElements.cosmetic.category.eq(findCategory)));
        return where;
    }



    private BooleanExpression distinguishBookmarkExistUser(QCosmetic cosmetic, User user) {
        return JPAExpressions
                .selectFrom(userCosmetic)
                .where(userIdEqBookmarkUserId(user).and(boardIdEqBookmarkBoardId(cosmetic)))
                .exists();
    }

    private BooleanExpression userIdEqBookmarkUserId(User user) {
        return userCosmetic.user.id.eq(user.getId());
    }

    private BooleanExpression boardIdEqBookmarkBoardId(QCosmetic cosmetic) {
        return userCosmetic.cosmetic.id.eq(cosmetic.id);
    }

    private BooleanExpression setFalse() {
        return Expressions.asBoolean(false);
    }


    private OrderSpecifier<? extends Serializable> getSortCosmeticElements(String sort) {
        if(sort.equals("asc"))
            return cosmeticElements.cosmetic.price.asc();
        else if(sort.equals("desc"))
            return cosmeticElements.cosmetic.price.desc();
            return null;
    }
    private OrderSpecifier<? extends Serializable> getSortCosmetic(String sort) {
        if(sort.equals("asc"))
            return cosmetic.price.asc();
        else if(sort.equals("desc"))
            return cosmetic.price.desc();
        return null;
    }

}
