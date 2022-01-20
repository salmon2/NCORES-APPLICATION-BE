package com.ncores.plaluvs.repository;

import com.ncores.plaluvs.domain.Category;
import com.ncores.plaluvs.domain.Cosmetic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CosmeticRepository extends JpaRepository<Cosmetic, Long> {
    List<Cosmetic> findTop20ByOrderByIdAsc();
    List<Cosmetic> findTop5ByOrderByIdAsc();


    Page<Cosmetic> findAllByCategory(Category category, Pageable pageable);
}
