package com.ncores.plaluvs;


import com.ncores.plaluvs.crawling.CrawlingItemDto;
import com.ncores.plaluvs.crawling.ReadJsonFile;
import com.ncores.plaluvs.domain.user.User;
import com.ncores.plaluvs.domain.user.UserRoleEnum;
import com.ncores.plaluvs.repository.UserRepository;
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
//@Component
public class SimpleListener implements ApplicationListener<ApplicationStartedEvent> {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ReadJsonFile readJsonFile;

    @SneakyThrows
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
//
//        readJsonFile.readJsonFile();
//        readJsonFile.saveJsonFile();
    }

}