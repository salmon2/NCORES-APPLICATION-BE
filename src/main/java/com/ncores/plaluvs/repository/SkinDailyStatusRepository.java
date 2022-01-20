package com.ncores.plaluvs.repository;

import com.ncores.plaluvs.domain.skintype.SkinType;
import com.ncores.plaluvs.domain.skintype.skindailyStatus.SkinDailyStatus;
import com.ncores.plaluvs.domain.skintype.skintrouble.SkinTrouble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkinDailyStatusRepository extends JpaRepository<SkinDailyStatus, Long> {
    void deleteAllBySkinType(SkinType findSkinType);

    List<SkinDailyStatus> findAllBySkinType(SkinType findSkinType);
}
