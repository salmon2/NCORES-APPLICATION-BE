package com.ncores.plaluvs.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class User {
    @GeneratedValue
    @Id
    private Long id;


    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    private String nickname;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private Long age;

    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(String username, String password, String nickname,  UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
    }
}
