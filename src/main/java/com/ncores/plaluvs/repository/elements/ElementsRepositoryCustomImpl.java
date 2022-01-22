package com.ncores.plaluvs.repository.elements;

import com.ncores.plaluvs.domain.Elements;
import com.ncores.plaluvs.domain.skintype.skintrouble.QSkinTrouble;
import com.ncores.plaluvs.domain.skintype.skintrouble.QSkinTroubleElements;
import com.ncores.plaluvs.domain.skintype.skintrouble.SkinTrouble;
import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
