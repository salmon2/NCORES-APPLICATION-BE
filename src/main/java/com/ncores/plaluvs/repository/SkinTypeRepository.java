package com.ncores.plaluvs.repository;

import com.ncores.plaluvs.domain.skintype.SkinType;
import com.ncores.plaluvs.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkinTypeRepository extends JpaRepository<SkinType, Long> {
    SkinType findByUser(User user);
}
