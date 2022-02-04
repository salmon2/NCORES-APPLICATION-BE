package com.ncores.plaluvs.repository;

import com.ncores.plaluvs.domain.Photo;
import com.ncores.plaluvs.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    Photo findTopByCreatedAtBetweenAndUser(LocalDateTime start, LocalDateTime endDatetime, User user);
}
