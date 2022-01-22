package com.ncores.plaluvs.repository;

import com.ncores.plaluvs.domain.UserCosmetic;
import com.ncores.plaluvs.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCosmeticRepository extends JpaRepository<UserCosmetic, Long>{

    List<UserCosmetic> findAllByUser(User user);

}
