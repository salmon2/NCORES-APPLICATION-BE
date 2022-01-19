package com.ncores.plaluvs.repository;

import com.ncores.plaluvs.domain.skintype.SkinType;
import com.ncores.plaluvs.domain.skintype.skindailystimulation.SkinDailyStimulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkinDailyStimulationRepository extends JpaRepository<SkinDailyStimulation, Long> {
    void deleteAllBySkinType(SkinType findSkinType);
}
