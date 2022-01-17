package com.ncores.plaluvs.repository;

import com.ncores.plaluvs.domain.CosmeticElements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemElementsRepository extends JpaRepository<CosmeticElements, Long> {
}
