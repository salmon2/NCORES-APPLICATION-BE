package com.ncores.plaluvs.repository;

import com.ncores.plaluvs.domain.SkinTypeGoodElements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkinTypeGoodElementsRepository extends JpaRepository<SkinTypeGoodElements, Long> {
}
