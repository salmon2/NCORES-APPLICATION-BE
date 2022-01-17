package com.ncores.plaluvs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ncores.plaluvs.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserCosmetic {
    @Id @GeneratedValue
    Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    @JsonIgnore
    Cosmetic cosmetic;
}
