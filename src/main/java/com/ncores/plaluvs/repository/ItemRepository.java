package com.ncores.plaluvs.repository;

import com.ncores.plaluvs.domain.Cosmetic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Cosmetic, Long> {
}
