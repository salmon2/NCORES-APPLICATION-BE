package com.ncores.plaluvs.repository.elements;


import com.ncores.plaluvs.domain.Elements;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ElementsRepository extends JpaRepository<Elements, Long>, ElementsRepositoryCustom {
    Elements findByKorean(String korean);
    List<Elements> findTop10ByOrderByIdAsc();
}
