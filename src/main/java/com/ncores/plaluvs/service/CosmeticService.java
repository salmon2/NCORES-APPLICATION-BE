package com.ncores.plaluvs.service;

import com.ncores.plaluvs.controller.cometic.dto.DetailCosmeticDto;
import com.ncores.plaluvs.controller.cometic.dto.SimpleCosmeticDto;
import com.ncores.plaluvs.domain.Cosmetic;
import com.ncores.plaluvs.exception.ErrorCode;
import com.ncores.plaluvs.exception.PlaluvsException;
import com.ncores.plaluvs.repository.CosmeticRepository;
import com.ncores.plaluvs.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CosmeticService {
    private final CosmeticRepository cosmeticRepository;


    public List<SimpleCosmeticDto> cosmeticSimpleRecommends(Long key, UserDetailsImpl userDetails) {

        List<SimpleCosmeticDto> result = new ArrayList<>();
        List<Cosmetic> findCosmetic = cosmeticRepository.findTop5ByOrderByIdAsc();

        for (int i = 0; i < 5; i++) {
            SimpleCosmeticDto simpleCosmeticDto =
                    new SimpleCosmeticDto(findCosmetic.get(i).getId(), findCosmetic.get(i).getItemImg(), findCosmetic.get(i).getItemName(),
                            (i % 2 == 0) ? Boolean.TRUE : Boolean.FALSE);
            i++;
            result.add(simpleCosmeticDto);
        }


        return result;
    }

    public List<DetailCosmeticDto> cosmeticDetailRecommends(String category, Long page) throws PlaluvsException {
        List<Cosmetic> findCosmetic = cosmeticRepository.findTop20ByOrderByIdAsc();
        List<DetailCosmeticDto> result = new ArrayList<>();

        for (int i = 0; i <20; i++) {
            DetailCosmeticDto detailCosmeticDto = new DetailCosmeticDto(
                    findCosmetic.get(i).getId(),
                    findCosmetic.get(i).getItemImg(), findCosmetic.get(i).getItemBrand(), findCosmetic.get(i).getItemName(),
                    findCosmetic.get(i).getPrice(), (i % 2 == 0) ? Boolean.TRUE : Boolean.FALSE
            );

            result.add(detailCosmeticDto);
        }
        if(page >= 4L)
            throw new PlaluvsException(ErrorCode.PAGE_OUT);

        return result;
    }

    public List<DetailCosmeticDto> cosmeticContainsElements(Long elements_id) {
        List<Cosmetic> findCosmetic = cosmeticRepository.findAll();
        List<DetailCosmeticDto> result = new ArrayList<>();

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

    public List<SimpleCosmeticDto> cosmeticWorry(UserDetailsImpl userDetails) {
        List<Cosmetic> findCosmetic = cosmeticRepository.findTop5ByOrderByIdAsc();
        List<SimpleCosmeticDto> result = new ArrayList<>();
        int i = 0;

        for (Cosmetic cosmetic : findCosmetic) {
            SimpleCosmeticDto simpleCosmeticDto = new SimpleCosmeticDto(
                    cosmetic.getId(),
                    cosmetic.getItemImg(),
                    cosmetic.getItemName(),
                    (i % 2 == 0) ? Boolean.TRUE : Boolean.FALSE
            );
            result.add(simpleCosmeticDto);
            i++;
        }

        return result;

    }
}
