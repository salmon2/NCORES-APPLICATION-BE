package com.ncores.plaluvs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ncores.plaluvs.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SkinTrouble {
    @Id
    @GeneratedValue
    Long id;

    @Enumerated(value = EnumType.STRING)
    SkinTroubleEnum trouble;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public SkinTrouble(SkinTroubleEnum trouble, User user) {
        this.trouble = trouble;
        this.user = user;
    }
}
