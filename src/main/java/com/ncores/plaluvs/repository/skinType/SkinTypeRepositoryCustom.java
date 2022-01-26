package com.ncores.plaluvs.repository.skinType;

import com.ncores.plaluvs.controller.skin.dto.ScoreData;
import com.ncores.plaluvs.controller.skin.dto.SkinStatusRecordResponseDto;
import com.ncores.plaluvs.domain.skintype.CurrentSkinStatus;
import com.ncores.plaluvs.domain.skintype.SkinType;
import com.ncores.plaluvs.domain.skintype.SkinTypeEnum;
import com.ncores.plaluvs.exception.PlaluvsException;
import com.ncores.plaluvs.security.UserDetailsImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SkinTypeRepositoryCustom {


    Page<SkinType> findAllCustom(UserDetailsImpl userDetails, PageRequest pageRequest, String sort);

    Double findAverageCustom(UserDetailsImpl userDetails);

    Page<SkinStatusRecordResponseDto> findskinStatusRecord(UserDetailsImpl userDetails, PageRequest pageRequest);

    List<ScoreData> findSkinStatusBoumanCustom(UserDetailsImpl userDetails, LocalDateTime startDatetime, LocalDateTime endDatetime, SkinTypeEnum sort) throws PlaluvsException;
}
