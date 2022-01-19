package com.ncores.plaluvs.repository;

import com.ncores.plaluvs.domain.SkinTypeBadElements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface STBadElementRepository extends JpaRepository<SkinTypeBadElements, Long> {
}
