package com.ncores.plaluvs.domain.skintype.skintrouble;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ncores.plaluvs.domain.Cosmetic;
import com.ncores.plaluvs.domain.Elements;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SkinTroubleElements {
    @Id @GeneratedValue
    Long id;

    @ManyToOne
    @JoinColumn(name = "skin_trouble_id")
    @JsonIgnore
    SkinTrouble skinTrouble;

    @ManyToOne
    @JoinColumn(name = "elements_id")
    @JsonIgnore
    Elements elements;

    public SkinTroubleElements(SkinTrouble skinTrouble, Elements elements) {
        this.skinTrouble = skinTrouble;
        this.elements = elements;
    }
}
