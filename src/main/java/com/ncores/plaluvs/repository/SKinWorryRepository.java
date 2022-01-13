package com.ncores.plaluvs.repository;

import com.ncores.plaluvs.domain.SkinTrouble;
import com.ncores.plaluvs.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SKinWorryRepository extends JpaRepository<SkinTrouble, Long> {
    List<SkinTrouble> findAllByUser(User user);

    void deleteAllByUser(User user);
}
