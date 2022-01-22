package com.ncores.plaluvs.repository.elements;

import com.ncores.plaluvs.domain.Elements;
import com.ncores.plaluvs.domain.skintype.skintrouble.SkinTrouble;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElementsRepositoryCustom {
    List<Elements> findAllBySkinTroubleCustom(List<SkinTrouble> skinTroubleList);
}
