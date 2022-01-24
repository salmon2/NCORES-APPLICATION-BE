package com.ncores.plaluvs.repository.elements;

import com.ncores.plaluvs.controller.UserElements;
import com.ncores.plaluvs.controller.skin.dto.QSkinElementsDto;
import com.ncores.plaluvs.controller.skin.dto.SkinElementsDto;
import com.ncores.plaluvs.domain.Elements;
import com.ncores.plaluvs.domain.dto.ElementsDto;
import com.ncores.plaluvs.domain.dto.QElementsDto;
import com.ncores.plaluvs.domain.skintype.SkinType;
import com.ncores.plaluvs.domain.skintype.skintrouble.SkinTrouble;
import com.ncores.plaluvs.domain.user.QUser;
import com.ncores.plaluvs.domain.user.User;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ncores.plaluvs.controller.QUserElements.userElements;
import static com.ncores.plaluvs.domain.QElements.elements;
import static com.ncores.plaluvs.domain.QSkinTypeGoodElements.skinTypeGoodElements;
import static com.ncores.plaluvs.domain.skintype.skintrouble.QSkinTrouble.skinTrouble;
import static com.ncores.plaluvs.domain.skintype.skintrouble.QSkinTroubleElements.skinTroubleElements;

@Repository
@RequiredArgsConstructor
public class ElementsRepositoryCustomImpl implements ElementsRepositoryCustom{
    private final JPAQueryFactory queryFactory;


    @Override
    public List<Elements> findAllBySkinTroubleCustom(List<SkinTrouble> skinTroubleList) {
        List<Elements> fetch = queryFactory
                .select(skinTroubleElements.elements)
                .from(skinTroubleElements)
                .join(skinTroubleElements.skinTrouble, skinTrouble)
                .where(skinTrouble.in(skinTroubleList))
                .fetch();

        return fetch;
    }

    @Override
    public List<Elements> findAllBySkinTypeGoodElements(SkinType skinType) {
        List<Elements> result = queryFactory
                .select(skinTypeGoodElements.elements)
                .from(skinTypeGoodElements)
                .where(skinTypeGoodElements.skinType.eq(skinType))
                .fetch();

        return result;
    }

    @Override
    public Page<ElementsDto> findAllByUserCustom(User user, PageRequest pageRequest) {
        List<ElementsDto> result = queryFactory
                .select(
                        new QElementsDto(
                                userElements.elements.id,
                                userElements.elements.korean,
                                userElements.elements.level
                        )
                )
                .from(userElements)
                .join(userElements.elements, elements)
                .join(userElements.user, QUser.user)
                .where(QUser.user.id.eq(user.getId()))
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch();

        JPAQuery<UserElements> countQuery = countDetailElemnetsByElement(user);

        return PageableExecutionUtils.getPage(result, pageRequest, countQuery::fetchCount);
    }

    @Override
    public List<SkinElementsDto> findSkinElementsDtoListBySkinTypeGoodElements(SkinType dailySkinTYpe) {
        List<SkinElementsDto> result = queryFactory
                .select(
                        new QSkinElementsDto(
                                skinTypeGoodElements.elements.id,
                                skinTypeGoodElements.elements.korean,
                                skinTypeGoodElements.elements.level
                        )
                )
                .from(skinTypeGoodElements)
                .where(skinTypeGoodElements.skinType.eq(dailySkinTYpe))
                .limit(8)
                .fetch();

        return result;
    }

    private JPAQuery<UserElements> countDetailElemnetsByElement(User user) {
        return queryFactory
                .selectFrom(userElements)
                .join(userElements.elements, elements)
                .join(userElements.user, QUser.user)
                .where(QUser.user.id.eq(user.getId()));

    }
}
