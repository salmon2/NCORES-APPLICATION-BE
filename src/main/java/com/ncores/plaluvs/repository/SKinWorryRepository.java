package com.ncores.plaluvs.repository;

import com.ncores.plaluvs.domain.SkinTrouble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SKinWorryRepository extends JpaRepository<SkinTrouble, Long> {
}
