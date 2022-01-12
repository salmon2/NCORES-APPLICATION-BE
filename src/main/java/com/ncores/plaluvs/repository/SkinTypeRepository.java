package com.ncores.plaluvs.repository;

import com.ncores.plaluvs.domain.SkinType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkinTypeRepository extends JpaRepository<SkinType, Long> {

}
