package com.ncores.plaluvs.service;

import com.ncores.plaluvs.controller.skin.dto.SkinElementsDto;
import com.ncores.plaluvs.controller.skin.dto.SkinStatusListResponseDto;
import com.ncores.plaluvs.controller.skin.dto.SkinStatusResponseDto;
import com.ncores.plaluvs.controller.skin.dto.StatusList;
import com.ncores.plaluvs.domain.*;
import com.ncores.plaluvs.domain.dto.OilStatusRequestDto;
import com.ncores.plaluvs.domain.dto.SkinWorryRequestDto;
import com.ncores.plaluvs.domain.skintrouble.SkinTrouble;
import com.ncores.plaluvs.domain.skintrouble.SkinTroubleEnum;
import com.ncores.plaluvs.domain.skintype.OilIndicate;
import com.ncores.plaluvs.domain.skintype.SkinType;
import com.ncores.plaluvs.exception.PlaluvsException;
import com.ncores.plaluvs.repository.ElementsRepository;
import com.ncores.plaluvs.repository.SKinWorryRepository;
import com.ncores.plaluvs.repository.SkinTypeRepository;
import com.ncores.plaluvs.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SkinService {
    private final SkinTypeRepository skinTypeRepository;
    private final SKinWorryRepository sKinWorryRepository;
    private final ElementsRepository elementsRepository;

    @Transactional
    public void skinOilIndicate(OilStatusRequestDto requestDto, UserDetailsImpl userDetails) throws PlaluvsException {
        OilIndicate oilIndicate = OilIndicate.findOilIndicate(requestDto.getSkinId());
        log.info("oilIndicate = {}", oilIndicate);

        SkinType findSkinType = skinTypeRepository.findByUser(userDetails.getUser());

        if(findSkinType == null) {
            findSkinType = new SkinType(oilIndicate, userDetails.getUser());
            skinTypeRepository.save(findSkinType);
        }
        else
            findSkinType.updateSkinType(oilIndicate);

        log.info("skinType = {}", findSkinType);
    }

    @Transactional
    public void skinWorryUpdate(SkinWorryRequestDto requestDto, UserDetailsImpl userDetails) throws PlaluvsException {
        sKinWorryRepository.deleteAllByUser(userDetails.getUser());

        for (Long id : requestDto.getSkinWorryId()) {
            SkinTroubleEnum skinTroubleEnum = SkinTroubleEnum.findSkinTroubleEnum(id);

            SkinTrouble skinTrouble = new SkinTrouble(skinTroubleEnum, userDetails.getUser());
            log.info("skinTrouble = {}", skinTrouble);

            sKinWorryRepository.save(skinTrouble);
        }
    }

    public SkinStatusResponseDto skinStatus() {
        List<Elements> findElements = elementsRepository.findTop5ByOrderByIdAsc();
        List<SkinElementsDto> skinElementsDtos = new ArrayList<>();
        for (Elements findElement : findElements) {
            SkinElementsDto skinElementsDto = new SkinElementsDto(findElement.getKorean(), "https://www.ewg.org/skindeep/squircle/show.svg?score=1&score_min=1");
            skinElementsDtos.add(skinElementsDto);
        }


        return new SkinStatusResponseDto("70점",
                "수분이 부족한 중/복합성 피부네요. 피부에 수분이 부족한 탓에 색소침착이 있네요. 다행히 피부가 저항성을 갖고 있어 외부 환경에 아주 민감 하진 않아요.그래도 주름과 색소침착을 예방하기 위해 자외선은 각별히 신경을 써주셔야해요.외출 시에는 꼭! 자외선 차단제를 자주 덧 발라주세요",
                70L,
                70L,
                70L,
                70L,
                skinElementsDtos
                );
    }

    public SkinStatusListResponseDto skinStatusList() {
        List<StatusList> newStatusList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            StatusList statusList = new StatusList("12월 " + (i + 29), String.valueOf(i + 80));
            newStatusList.add(statusList);
        }

        SkinStatusListResponseDto result = new SkinStatusListResponseDto(newStatusList, "지난주 보다 피부 점수가 20점 상승했어요", "어제보다 민감한 피부가 진정되었어요");
        return result;
    }
}
