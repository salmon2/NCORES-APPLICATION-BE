package com.ncores.plaluvs;


import com.ncores.plaluvs.crawling.ReadJsonFile;
import com.ncores.plaluvs.domain.user.User;
import com.ncores.plaluvs.domain.user.UserRoleEnum;
import com.ncores.plaluvs.repository.skinType.SkinTypeRepository;
import com.ncores.plaluvs.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@NoArgsConstructor
//@Component
public class SimpleListener implements ApplicationListener<ApplicationStartedEvent> {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ReadJsonFile readJsonFile;
    @Autowired
    private SkinTypeRepository skinTypeRepository;

    @SneakyThrows
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        userRepository.deleteAll();

        User newUser2 = new User(
                "dys04076@naver.com",
                passwordEncoder.encode("asdf"),
                "test",
                UserRoleEnum.ADMIN
        );

        userRepository.save(newUser2);

        User newUser1 = new User(
                "asdf1234",
                passwordEncoder.encode("asdf1234"),
                "test",
                UserRoleEnum.ADMIN
        );

        userRepository.save(newUser1);
    }

}