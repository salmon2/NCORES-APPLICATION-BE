package com.ncores.plaluvs.repository.cosmetic;


import com.ncores.plaluvs.controller.cometic.dto.QSimpleCosmeticDto;
import com.ncores.plaluvs.controller.cometic.dto.SimpleCosmeticDto;
import com.ncores.plaluvs.domain.Elements;
import com.ncores.plaluvs.domain.QElements;
import com.ncores.plaluvs.domain.QUserCosmetic;
import com.ncores.plaluvs.domain.skintype.skintrouble.QSkinTroubleElements;
import com.ncores.plaluvs.domain.user.User;
import com.ncores.plaluvs.security.UserDetailsImpl;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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
                                (userDetails == null) ? setFalse() : distinguishBookmarkExistUser(userDetails.getUser())
                        )
                )
                .from(cosmeticElements)
                .join(cosmeticElements.elements, elements)
                .where(cosmeticElements.elements.in(findElements))
                .limit(5)
                .fetch();

        return result;
    }

    private BooleanExpression distinguishBookmarkExistUser(User user) {
        return JPAExpressions
                .selectFrom(userCosmetic)
                .where(userIdEqBookmarkUserId(user).and(boardIdEqBookmarkBoardId()))
                .exists();
    }

    private BooleanExpression userIdEqBookmarkUserId(User user) {
        return userCosmetic.user.id.eq(user.getId());
    }

    private BooleanExpression boardIdEqBookmarkBoardId() {
        return userCosmetic.cosmetic.id.eq(cosmetic.id);
    }

    private BooleanExpression setFalse() {
        return Expressions.asBoolean(false);
    }

}
