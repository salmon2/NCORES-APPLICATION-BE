package com.ncores.plaluvs.repository.skinType;

import com.ncores.plaluvs.controller.skin.dto.SkinStatusRecordResponseDto;
import com.ncores.plaluvs.domain.skintype.SkinType;
import com.ncores.plaluvs.security.UserDetailsImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
public interface SkinTypeRepositoryCustom {


    Page<SkinType> findAllCustom(UserDetailsImpl userDetails, PageRequest pageRequest, String sort);

    Double findAverageCustom(UserDetailsImpl userDetails);

    Page<SkinStatusRecordResponseDto> findskinStatusRecord(UserDetailsImpl userDetails, PageRequest pageRequest);
}
