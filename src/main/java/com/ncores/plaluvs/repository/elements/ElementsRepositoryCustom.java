package com.ncores.plaluvs.repository.elements;

import com.ncores.plaluvs.controller.skin.dto.SkinElementsDto;
import com.ncores.plaluvs.domain.Elements;
import com.ncores.plaluvs.domain.dto.ElementsDto;
import com.ncores.plaluvs.domain.skintype.SkinType;
import com.ncores.plaluvs.domain.skintype.skintrouble.SkinTrouble;
import com.ncores.plaluvs.domain.user.User;
import com.ncores.plaluvs.security.UserDetailsImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElementsRepositoryCustom {
    List<Elements> findAllBySkinTroubleCustom(List<SkinTrouble> skinTroubleList);

    List<Elements> findAllBySkinTypeGoodElements(SkinType skinTypeGoodElementsList);

    Page<ElementsDto> findAllByUserCustom(User user, PageRequest pageRequest);

    List<SkinElementsDto> findSkinElementsDtoListBySkinTypeGoodElements(SkinType dailySkinTYpe, UserDetailsImpl userDetails);
}
