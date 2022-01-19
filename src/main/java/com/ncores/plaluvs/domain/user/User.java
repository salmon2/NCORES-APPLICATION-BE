package com.ncores.plaluvs.domain.user;

import com.ncores.plaluvs.domain.Photo;
import com.ncores.plaluvs.domain.Timestamped;
import com.ncores.plaluvs.domain.skintrouble.SkinTrouble;
import com.ncores.plaluvs.domain.skintype.SkinType;
import com.ncores.plaluvs.domain.UserCosmetic;
import com.ncores.plaluvs.exception.ErrorCode;
import com.ncores.plaluvs.exception.PlaluvsException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class User extends Timestamped {
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SkinType> skinType;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SkinTrouble> skinTrouble = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserCosmetic> userCosmeticList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Photo> photo = new ArrayList<>();


    public User(String username, String password, String nickname,  UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
    }

    public void changeAge(Long age){
        this.age = age;
    }

    public void changeGender(String gender) throws PlaluvsException {
        Gender findGender = Gender.findGender(gender);
        if (findGender == null)
            throw new PlaluvsException(ErrorCode.GENDER_NOT_EXIST);
        this.gender = findGender;
    }

}
