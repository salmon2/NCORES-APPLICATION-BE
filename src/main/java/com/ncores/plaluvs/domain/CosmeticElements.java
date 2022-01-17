package com.ncores.plaluvs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class CosmeticElements {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @JsonIgnore
    Cosmetic cosmetic;

    @ManyToOne
    @JoinColumn(name = "elements_id")
    @JsonIgnore
    Elements elements;

    public CosmeticElements(Cosmetic cosmetic, Elements elements) {
        this.cosmetic = cosmetic;
        this.elements = elements;
    }
}
