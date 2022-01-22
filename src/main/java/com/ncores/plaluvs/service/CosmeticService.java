package com.ncores.plaluvs.service;

import com.ncores.plaluvs.controller.cometic.dto.DetailCosmeticDto;
import com.ncores.plaluvs.controller.cometic.dto.SimpleCosmeticDto;
import com.ncores.plaluvs.domain.*;
import com.ncores.plaluvs.domain.skintype.SkinType;
import com.ncores.plaluvs.domain.skintype.skintrouble.*;
import com.ncores.plaluvs.exception.ErrorCode;
import com.ncores.plaluvs.exception.PlaluvsException;
import com.ncores.plaluvs.repository.CategoryRepository;
import com.ncores.plaluvs.repository.cosmetic.CosmeticRepository;
import com.ncores.plaluvs.repository.ElementsRepository;
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


    public List<SimpleCosmeticDto> cosmeticSimpleRecommends(UserDetailsImpl userDetails) throws PlaluvsException {
        SkinType dailySkinType = skinTypeRepository.findDailySkinTypeException(userDetails);
        List<SimpleCosmeticDto> result = null;

        return null;
    }

    public List<DetailCosmeticDto> cosmeticDetailRecommends(UserDetailsImpl userDetails, Long categoryId, Long page) throws PlaluvsException {
        List<DetailCosmeticDto> result = new ArrayList<>();
        SkinType dailySkinType = skinTypeRepository.findDailySkinType(userDetails);
        PageRequest paging = PageRequest.of(page.intValue(), 20, Sort.by("price").ascending());

        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new PlaluvsException(ErrorCode.CATEGORY_NOT_FOUND)
        );


        if(dailySkinType.getBouman().equals("ORNT")){
            Page<Cosmetic> allByCategory = cosmeticRepository.findAllByCategory(category, paging);
            for (Cosmetic cosmetic : allByCategory) {
                DetailCosmeticDto detailCosmeticDto = new DetailCosmeticDto(cosmetic.getId(), cosmetic.getItemImg(), cosmetic.getItemBrand(), cosmetic.getItemName(), cosmetic.getPrice(),
                        Boolean.TRUE);
                result.add(detailCosmeticDto);
            }
        }
        else{
            List<SkinTypeGoodElements> skinTypeGoodElementsList = dailySkinType.getSkinTypeGoodElementsList();
            for (SkinTypeGoodElements skinTypeGoodElements : skinTypeGoodElementsList) {
                List<CosmeticElements> cosmeticElementsList = skinTypeGoodElements.getElements().getCosmeticElementsList();
                for (CosmeticElements cosmeticElements : cosmeticElementsList) {
                    Cosmetic cosmetic = cosmeticElements.getCosmetic();
                    if(cosmetic.getCategory().getId().equals(category.getId())){
                        DetailCosmeticDto detailCosmeticDto = new DetailCosmeticDto(cosmetic.getId(), cosmetic.getItemImg(), cosmetic.getItemBrand(), cosmetic.getItemName(), cosmetic.getPrice(),
                                Boolean.TRUE);
                        result.add(detailCosmeticDto);
                    }
                    else
                        continue;
                }
            }

            Set<DetailCosmeticDto> set = new HashSet<>(result);
            result = new ArrayList<>(set);
            if(result.size() >20)
                result = result.subList(0,20);
        }

        return result;
    }

    public List<DetailCosmeticDto> cosmeticContainsElements(Long elements_id, Long categoryId, Long page) throws PlaluvsException {
        List<Cosmetic> findCosmetic = cosmeticRepository.findAll();
        List<DetailCosmeticDto> result = new ArrayList<>();

        categoryRepository.findById(categoryId).orElseThrow(
                ()->  new PlaluvsException(ErrorCode.CATEGORY_NOT_FOUND)
        );


        for (int i = 0; i <20; i++) {
            DetailCosmeticDto detailCosmeticDto = new DetailCosmeticDto(
                    findCosmetic.get(i).getId(),
                    findCosmetic.get(i).getItemImg(), findCosmetic.get(i).getItemBrand(), findCosmetic.get(i).getItemName(),
                    findCosmetic.get(i).getPrice(), (i % 2 == 0) ? Boolean.TRUE : Boolean.FALSE
            );

            result.add(detailCosmeticDto);
        }

        return result;
    }

    public List<SimpleCosmeticDto> cosmeticWorry(UserDetailsImpl userDetails) throws PlaluvsException {
        List<SimpleCosmeticDto> result = new ArrayList<>();
        SkinType findSkinType = skinTypeRepository.findTopByUserOrderByCreatedAtDesc(userDetails.getUser());
        List<SkinTrouble> skinTroubleList = findSkinType.getSkinTroubleList();

        if(skinTroubleList == null)
            throw new PlaluvsException(ErrorCode.SKIN_WORRY_EMPTY);

        for (SkinTrouble skinTrouble : skinTroubleList) {
            SkinTroubleEnum trouble = skinTrouble.getTrouble();

            if(trouble.getId().equals(1L)){
                for (TroubleSkinElements value : TroubleSkinElements.values()) {
                    saveSimpleCosmetic(value.getName(), result);
                }
            }
            if(trouble.getId().equals(2L)){
                for (WrinklesSkinElements value :WrinklesSkinElements.values()) {
                    saveSimpleCosmetic(value.getName(), result);
                }
            }

            if(trouble.getId().equals(3L)){
                for (SensitiveSkinElements value : SensitiveSkinElements.values()) {
                    saveSimpleCosmetic(value.getName(), result);
                }
            }

            if(trouble.getId().equals(4L)){
                for (PigmentationSkinElements value : PigmentationSkinElements.values()) {
                    saveSimpleCosmetic(value.getName(), result);
                }
            }

            if(trouble.getId().equals(5L)){
                for (UnbalanceSkinElements value : UnbalanceSkinElements.values()) {
                    saveSimpleCosmetic(value.getName(), result);
                }
            }
        }

        Collections.shuffle(result);

        return result.subList(0,5);
    }

    private void saveSimpleCosmetic(String value, List<SimpleCosmeticDto> result) {
        Elements findElements = elementsRepository.findByKorean(value);
        if(findElements == null)
            return;
        List<CosmeticElements> cosmeticElementsList = findElements.getCosmeticElementsList();
        for (CosmeticElements cosmeticElements : cosmeticElementsList) {
            SimpleCosmeticDto simpleCosmeticDto = new SimpleCosmeticDto(cosmeticElements.getCosmetic().getId(), cosmeticElements.getCosmetic().getItemImg()
                    , cosmeticElements.getCosmetic().getItemName(), Boolean.FALSE);
            result.add(simpleCosmeticDto);

            // List를 Set으로 변경
            Set<SimpleCosmeticDto> set = new HashSet<SimpleCosmeticDto>(result);
            // Set을 List로 변경
            result  = new ArrayList<SimpleCosmeticDto>(set);
        }


    }
}
