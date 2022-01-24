package com.ncores.plaluvs.repository;

import com.ncores.plaluvs.controller.UserElements;
import com.ncores.plaluvs.domain.Elements;
import com.ncores.plaluvs.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserElementsRepository extends JpaRepository<UserElements, Long> {
    UserElements findByUserAndElements(User user, Elements elements);

}
