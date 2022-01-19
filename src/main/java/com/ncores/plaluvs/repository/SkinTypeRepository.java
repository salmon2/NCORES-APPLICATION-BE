package com.ncores.plaluvs.repository;

import com.ncores.plaluvs.domain.skintype.SkinType;
import com.ncores.plaluvs.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SkinTypeRepository extends JpaRepository<SkinType, Long> {
    SkinType findTopByUserOrderByCreatedAtAsc(User user);
    SkinType findTopByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    SkinType findTopByUserAndCreatedAtBetween(User user, LocalDateTime startDatetime, LocalDateTime endDatetime);

}
