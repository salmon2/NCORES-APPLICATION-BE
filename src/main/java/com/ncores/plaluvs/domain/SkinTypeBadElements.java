package com.ncores.plaluvs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ncores.plaluvs.domain.skintype.SkinType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SkinTypeBadElements {

    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "skinType_id")
    @JsonIgnore
    private SkinType skinType;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "elements_id")
    @JsonIgnore
    private Elements elements;

    private String description;

}
