package com.ncores.plaluvs.repository;

import com.ncores.plaluvs.domain.skintype.skintrouble.SkinTrouble;
import com.ncores.plaluvs.domain.skintype.skintrouble.SkinTroubleElements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkinTroubleElementsRepository extends JpaRepository<SkinTroubleElements, Long> {
    List<SkinTroubleElements> findALlBySkinTrouble(SkinTrouble skinTrouble);
}
