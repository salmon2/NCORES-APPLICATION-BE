package com.ncores.plaluvs.domain.skintype.skintrouble;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ncores.plaluvs.domain.skintype.SkinType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @JoinColumn(name = "skin_type_id")
    @JsonIgnore
    private SkinType skinType;

    public SkinTrouble(SkinTroubleEnum trouble, SkinType skinType) {
        this.trouble = trouble;
        this.skinType = skinType;
    }
}
