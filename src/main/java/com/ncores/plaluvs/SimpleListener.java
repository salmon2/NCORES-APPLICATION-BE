package com.ncores.plaluvs;


import com.ncores.plaluvs.domain.User;
import com.ncores.plaluvs.domain.UserRoleEnum;
import com.ncores.plaluvs.repository.UserRepository;
import com.ncores.plaluvs.service.UserService;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@NoArgsConstructor
@Component
public class SimpleListener implements ApplicationListener<ApplicationStartedEvent> {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationStartedEvent event) {
        User newUser = new User(
            "dys04076@naver.com",
            passwordEncoder.encode("asdf"),
            "salmon",
            UserRoleEnum.USER
        );

        userRepository.save(newUser);
    }

}