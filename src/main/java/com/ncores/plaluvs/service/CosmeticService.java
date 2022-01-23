package com.ncores.plaluvs.service;

import com.ncores.plaluvs.controller.cometic.dto.DetailCosmeticDto;
import com.ncores.plaluvs.controller.cometic.dto.SimpleCosmeticDto;
import com.ncores.plaluvs.domain.*;
import com.ncores.plaluvs.domain.skintype.SkinType;
import com.ncores.plaluvs.domain.skintype.skintrouble.*;
import com.ncores.plaluvs.exception.ErrorCode;
import com.ncores.plaluvs.exception.PlaluvsException;
import com.ncores.plaluvs.repository.CategoryRepository;
import com.ncores.plaluvs.repository.UserCosmeticRepository;
import com.ncores.plaluvs.repository.cosmetic.CosmeticRepository;
import com.ncores.plaluvs.repository.elements.ElementsRepository;
import com.ncores.plaluvs.repository.SkinTypeRepository;
import com.ncores.plaluvs.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CosmeticService {
    private final CosmeticRepository cosmeticRepository;
    private final ElementsRepository elementsRepository;
    private final SkinTypeRepository skinTypeRepository;
    private final CategoryRepository categoryRepository;
    private final UserCosmeticRepository userCosmeticRepository;

    public List<SimpleCosmeticDto> cosmeticSimpleRecommends(UserDetailsImpl userDetails) throws PlaluvsException {
        SkinType dailySkinType = skinTypeRepository.findDailySkinTypeOrLatestSkinType(userDetails);
        List<Elements> elements = elementsRepository.findAllBySkinTypeGoodElements(dailySkinType);


        List<SimpleCosmeticDto> result = cosmeticRepository.findAllbyBouman(userDetails, elements);

        return result;
    }

    public Page<DetailCosmeticDto> cosmeticDetailRecommends(UserDetailsImpl userDetails, Long categoryId, Long page, String sort) throws PlaluvsException {
        PageRequest pageRequest = PageRequest.of(page.intValue(), 20);

        SkinType dailySkinType = skinTypeRepository.findDailySkinTypeOrLatestSkinType(userDetails);
        List<Elements> findElements = elementsRepository.findAllBySkinTypeGoodElements(dailySkinType);

        Category findCategory = categoryRepository.findById(categoryId).orElseThrow(
                () -> new PlaluvsException(ErrorCode.CATEGORY_NOT_FOUND)
        );

        Page<DetailCosmeticDto> result = cosmeticRepository.findAllByCategoryAndBouman(userDetails, findElements, findCategory, pageRequest, sort);


        return result;
    }

    public Page<DetailCosmeticDto> cosmeticContainsElements(UserDetailsImpl userDetails, Long elementsId, Long categoryId, Long page, String sort) throws PlaluvsException {
        Elements findElements = elementsRepository.findById(elementsId).orElseThrow(
                () -> new PlaluvsException(ErrorCode.ELEMENT_NOT_FOUND)
        );

        Category findCategory = categoryRepository.findById(categoryId).orElseThrow(
                () -> new PlaluvsException(ErrorCode.CATEGORY_NOT_FOUND)
        );

        PageRequest pageRequest = PageRequest.of(page.intValue(), 20);


        Page<DetailCosmeticDto> result = cosmeticRepository.findCosmeticByElementsCustom(userDetails, findElements, findCategory, pageRequest, sort);

        return result;
    }

    public List<SimpleCosmeticDto> cosmeticWorry(UserDetailsImpl userDetails) throws PlaluvsException {
        UserDetailsImpl.UserCheck(userDetails);

        SkinType dailySkinType = skinTypeRepository.findDailySkinTypeException(userDetails);
        List<SkinTrouble> skinTroubleList = dailySkinType.getSkinTroubleList();
        List<Elements> elements = elementsRepository.findAllBySkinTroubleCustom(skinTroubleList);


        List<SimpleCosmeticDto> result = cosmeticRepository.findCosmeticWorry(elements, userDetails);

        return result;
    }

    public UserCosmetic cosmeticMark(UserDetailsImpl userDetails, Long cosmetic) throws PlaluvsException {
        UserDetailsImpl.UserCheck(userDetails);

        Cosmetic findCosmetic = cosmeticRepository.findById(cosmetic).orElseThrow(
                () -> new PlaluvsException(ErrorCode.COSMETIC_NOT_FOUND)
        );

        UserCosmetic newUserCosmetic = new UserCosmetic(userDetails.getUser(), findCosmetic);
        UserCosmetic save = userCosmeticRepository.save(newUserCosmetic);
        return save;
    }
}
