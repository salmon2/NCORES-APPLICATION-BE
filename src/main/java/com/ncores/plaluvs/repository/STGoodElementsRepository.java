package com.ncores.plaluvs.repository;

import com.ncores.plaluvs.domain.SkinTypeGoodElements;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface STGoodElementsRepository extends JpaRepository<SkinTypeGoodElements, Long> {

}
