package com.ncores.plaluvs.repository;

import com.ncores.plaluvs.domain.skintype.skintrouble.SkinTrouble;
import com.ncores.plaluvs.domain.skintype.SkinType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SKinWorryRepository extends JpaRepository<SkinTrouble, Long> {
    List<SkinTrouble> findAllBySkinType(SkinType skinType);

    void deleteAllBySkinType(SkinType skinType);
}
