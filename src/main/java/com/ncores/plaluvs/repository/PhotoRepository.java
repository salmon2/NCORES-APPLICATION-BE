package com.ncores.plaluvs.repository;

import com.ncores.plaluvs.domain.Photo;
import com.ncores.plaluvs.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
