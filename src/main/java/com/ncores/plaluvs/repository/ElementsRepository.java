package com.ncores.plaluvs.repository;


import com.ncores.plaluvs.domain.Elements;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElementsRepository extends JpaRepository<Elements, Long> {
    Elements findByKorean(String korean);
}
