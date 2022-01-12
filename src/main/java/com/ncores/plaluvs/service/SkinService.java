package com.ncores.plaluvs.service;

import com.ncores.plaluvs.domain.OilIndicate;
import com.ncores.plaluvs.domain.SkinTrouble;
import com.ncores.plaluvs.domain.SkinTroubleEnum;
import com.ncores.plaluvs.domain.SkinType;
import com.ncores.plaluvs.domain.dto.OilStatusRequestDto;
import com.ncores.plaluvs.domain.dto.SkinWorryRequestDto;
import com.ncores.plaluvs.exception.PlaluvsException;
import com.ncores.plaluvs.repository.SKinWorryRepository;
import com.ncores.plaluvs.repository.SkinTypeRepository;
import com.ncores.plaluvs.repository.UserRepository;
import com.ncores.plaluvs.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class SkinService {
    private final UserRepository userRepository;
    private final SkinTypeRepository skinTypeRepository;
    private final SKinWorryRepository sKinWorryRepository;

    @Transactional
    public void skinOilIndicate(OilStatusRequestDto requestDto, UserDetailsImpl userDetails) throws PlaluvsException {
        OilIndicate oilIndicate = OilIndicate.findOilIndicate(requestDto.getSkinId());
        SkinType skinType = new SkinType(oilIndicate, userDetails.getUser());

        skinTypeRepository.save(skinType);
    }

    @Transactional
    public void skinWorryUpdate(SkinWorryRequestDto requestDto, UserDetailsImpl userDetails) throws PlaluvsException {
        for (Long id : requestDto.getSkinWorryId()) {
            SkinTroubleEnum skinTroubleEnum = SkinTroubleEnum.findSkinTroubleEnum(id);
            SkinTrouble skinTrouble = new SkinTrouble(skinTroubleEnum, userDetails.getUser());
            sKinWorryRepository.save(skinTrouble);
        }
    }
}
