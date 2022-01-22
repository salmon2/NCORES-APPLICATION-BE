package com.ncores.plaluvs.repository;

import com.ncores.plaluvs.domain.skintype.skintrouble.SkinTroubleElements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkinTroubleElementsRepository extends JpaRepository<SkinTroubleElements, Long> {
}
