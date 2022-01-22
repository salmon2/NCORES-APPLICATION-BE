package com.ncores.plaluvs.repository.cosmetic;

import com.ncores.plaluvs.controller.cometic.dto.SimpleCosmeticDto;
import com.ncores.plaluvs.domain.Elements;
import com.ncores.plaluvs.security.UserDetailsImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CosmeticRepositoryCustom {
    List<SimpleCosmeticDto> findCosmeticWorry(List<Elements> skinTroubleElements, UserDetailsImpl userDetails);
}
