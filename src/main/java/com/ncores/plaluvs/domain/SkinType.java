package com.ncores.plaluvs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ncores.plaluvs.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SkinType {
    @Id
    @GeneratedValue
    Long id;

    @Enumerated(value = EnumType.STRING)
    OilIndicate oilIndicate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public SkinType(OilIndicate oilIndicate, User user) {
        this.oilIndicate = oilIndicate;
        this.user = user;
    }
}
