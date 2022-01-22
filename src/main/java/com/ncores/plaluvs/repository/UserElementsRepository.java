package com.ncores.plaluvs.repository;

import com.ncores.plaluvs.controller.UserElements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserElementsRepository extends JpaRepository<UserElements, Long> {



}
