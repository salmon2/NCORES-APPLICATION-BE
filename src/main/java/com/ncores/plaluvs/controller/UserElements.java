package com.ncores.plaluvs.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ncores.plaluvs.domain.Cosmetic;
import com.ncores.plaluvs.domain.Elements;
import com.ncores.plaluvs.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserElements {
    @Id @GeneratedValue
    Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    @JsonIgnore
    Elements elements;

    public UserElements(User user, Elements elements) {
        this.user = user;
        this.elements = elements;
    }
}
