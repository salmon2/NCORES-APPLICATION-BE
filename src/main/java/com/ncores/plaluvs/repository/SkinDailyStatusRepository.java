package com.ncores.plaluvs.repository;

import com.ncores.plaluvs.domain.skintype.SkinType;
import com.ncores.plaluvs.domain.skintype.skindailyStatus.SkinDailyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkinDailyStatusRepository extends JpaRepository<SkinDailyStatus, Long> {
    void deleteAllBySkinType(SkinType findSkinType);
}
