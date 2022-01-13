package com.ncores.plaluvs.service;

import com.ncores.plaluvs.domain.OilIndicate;
import com.ncores.plaluvs.domain.SkinTrouble;
import com.ncores.plaluvs.domain.SkinTroubleEnum;
import com.ncores.plaluvs.domain.SkinType;
import com.ncores.plaluvs.domain.dto.OilStatusRequestDto;
import com.ncores.plaluvs.domain.dto.SkinWorryRequestDto;
import com.ncores.plaluvs.domain.user.User;
import com.ncores.plaluvs.exception.PlaluvsException;
import com.ncores.plaluvs.repository.SKinWorryRepository;
import com.ncores.plaluvs.repository.SkinTypeRepository;
import com.ncores.plaluvs.repository.UserRepository;
import com.ncores.plaluvs.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SkinService {
    private final SkinTypeRepository skinTypeRepository;
    private final SKinWorryRepository sKinWorryRepository;

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
}
