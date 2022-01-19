package com.ncores.plaluvs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ncores.plaluvs.domain.skintype.SkinType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SkinTypeBadElements {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "skinType_id")
    @JsonIgnore
    private SkinType skinType;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "elements_id")
    @JsonIgnore
    private Elements elements;

    private String type;

    public SkinTypeBadElements(SkinType skinType, Elements elements, String type) {
        this.skinType = skinType;
        this.elements = elements;
        this.type = type;
    }
}
