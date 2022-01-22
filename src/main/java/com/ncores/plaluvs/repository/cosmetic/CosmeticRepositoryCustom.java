package com.ncores.plaluvs.repository.cosmetic;

import com.ncores.plaluvs.controller.cometic.dto.DetailCosmeticDto;
import com.ncores.plaluvs.controller.cometic.dto.SimpleCosmeticDto;
import com.ncores.plaluvs.controller.user.dto.CosmeticDto;
import com.ncores.plaluvs.domain.Category;
import com.ncores.plaluvs.domain.Cosmetic;
import com.ncores.plaluvs.domain.Elements;
import com.ncores.plaluvs.domain.user.User;
import com.ncores.plaluvs.security.UserDetailsImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CosmeticRepositoryCustom {
    List<SimpleCosmeticDto> findCosmeticWorry(List<Elements> skinTroubleElements, UserDetailsImpl userDetails);

    List<SimpleCosmeticDto> findAllbyBouman(UserDetailsImpl userDetails, List<Elements> elements);

    Page<DetailCosmeticDto> findAllByCategoryAndBouman(UserDetailsImpl userDetails, List<Elements> findElements, Category findCategory, PageRequest pageRequest, String sort);

    Page<DetailCosmeticDto> findCosmeticByElementsCustom(UserDetailsImpl userDetails, Elements findElements, Category findCategory, PageRequest pageRequest, String sort);

    Page<CosmeticDto> findAllByUserCustom(User user, PageRequest pageRequest);

}
