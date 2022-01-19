package com.ncores.plaluvs.service;

import com.ncores.plaluvs.controller.skin.dto.*;
import com.ncores.plaluvs.domain.*;
import com.ncores.plaluvs.domain.dto.OilStatusRequestDto;
import com.ncores.plaluvs.domain.dto.SkinWorryRequestDto;
import com.ncores.plaluvs.domain.skintype.skindailyStatus.SkinDailyStatus;
import com.ncores.plaluvs.domain.skintype.skindailyStatus.SkinDailyStatusEnum;
import com.ncores.plaluvs.domain.skintype.skindailystimulation.SkinDailyStimulation;
import com.ncores.plaluvs.domain.skintype.skindailystimulation.SkinDailyStimulationEnum;
import com.ncores.plaluvs.domain.skintype.skintrouble.SkinTrouble;
import com.ncores.plaluvs.domain.skintype.skintrouble.SkinTroubleEnum;
import com.ncores.plaluvs.domain.skintype.CurrentSkinStatus;
import com.ncores.plaluvs.domain.skintype.SkinType;
import com.ncores.plaluvs.exception.PlaluvsException;
import com.ncores.plaluvs.repository.*;
import com.ncores.plaluvs.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SkinService {
    private final SkinTypeRepository skinTypeRepository;
    private final SKinWorryRepository sKinWorryRepository;
    private final SkinDailyStatusRepository skinDailyStatusRepository;
    private final ElementsRepository elementsRepository;
    private final SkinDailyStimulationRepository skinDailyStimulationRepository;


    @Transactional
    public void currentSkinStatus(OilStatusRequestDto requestDto, UserDetailsImpl userDetails) throws PlaluvsException {
        CurrentSkinStatus currentSkinStatus = CurrentSkinStatus.findQuestionOne(requestDto.getSkinId());
        log.info("oilIndicate = {}", currentSkinStatus);

        SkinType findSkinType = findDailySkinType(userDetails);

        if(findSkinType == null) {
            findSkinType = new SkinType(currentSkinStatus, userDetails.getUser());
            skinTypeRepository.save(findSkinType);
        }
        else
            findSkinType.updateSkinType(currentSkinStatus);

        log.info("skinType = {}", findSkinType);
    }



    @Transactional
    public void skinWorryUpdate(SkinWorryRequestDto requestDto, UserDetailsImpl userDetails) throws PlaluvsException {
        SkinType findSkinType = findDailySkinType(userDetails);
        SkinType.skinTypeCheck(findSkinType);

        sKinWorryRepository.deleteAllBySkinType(findSkinType);


        for (Long id : requestDto.getSkinWorryId()) {
            SkinTroubleEnum skinTroubleEnum = SkinTroubleEnum.findSkinTroubleEnum(id);

            SkinTrouble skinTrouble = new SkinTrouble(skinTroubleEnum, findSkinType);
            log.info("skinTrouble = {}", skinTrouble);

            sKinWorryRepository.save(skinTrouble);
        }
    }


    @Transactional
    public void skinDailyStatus(SkinDailyStatusRequestDto requestDto, UserDetailsImpl userDetails) throws PlaluvsException {
        SkinType findSkinType = findDailySkinType(userDetails);
        SkinType.skinTypeCheck(findSkinType);

        skinDailyStatusRepository.deleteAllBySkinType(findSkinType);

        for (Long id : requestDto.getId()) {
            SkinDailyStatusEnum skinDailyStatusEnum = SkinDailyStatusEnum.findDailySkinEnum(id);

            SkinDailyStatus skinDailyStatus = new SkinDailyStatus(skinDailyStatusEnum, findSkinType);
            log.info("skinTrouble = {}", skinDailyStatus);

            skinDailyStatusRepository.save(skinDailyStatus);
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


    private SkinType findDailySkinType(UserDetailsImpl userDetails) {
        LocalDateTime startDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0)); //오늘 00:00:00
        LocalDateTime endDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59)); //오늘 23:59:59

        SkinType findSkinType = skinTypeRepository.findTopByUserAndCreatedAtBetween(userDetails.getUser(), startDatetime, endDatetime);
        return findSkinType;
    }

    @Transactional
    public void skinDailyStimulation(SkinDailyStimulationRequestDto requestDto, UserDetailsImpl userDetails) throws PlaluvsException {
        SkinType findSkinType = findDailySkinType(userDetails);
        SkinType.skinTypeCheck(findSkinType);

        skinDailyStimulationRepository.deleteAllBySkinType(findSkinType);

        for (Long id : requestDto.getId()) {
            SkinDailyStimulationEnum skinDailyStimulationEnum = SkinDailyStimulationEnum.findSkinDailyStimulationEnum(id);

            SkinDailyStimulation skinDailyStimulation = new SkinDailyStimulation(skinDailyStimulationEnum, findSkinType);
            log.info("skinTrouble = {}", skinDailyStimulation);

            skinDailyStimulationRepository.save(skinDailyStimulation);
        }
    }

    @Transactional
    public void skinSelfCheck(SkinDailySefCheckRequestDto requestDto, UserDetailsImpl userDetails) throws PlaluvsException {
        SkinType findSkinType = findDailySkinType(userDetails);
        SkinType.skinTypeCheck(findSkinType);

        findSkinType.setSelfScore(requestDto.getScore());
    }
}
