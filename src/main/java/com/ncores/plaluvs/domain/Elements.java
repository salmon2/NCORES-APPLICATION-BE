package com.ncores.plaluvs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Elements {
    @Id @GeneratedValue
    private Long id;

    private String level;
    private String korean;
    private String english;
    private String purpose;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    @JsonIgnore
    private Item item;

    public Elements(String level, String korean, String english, String purpose, Item item) {
        this.level = level;
        this.korean = korean;
        this.english = english;
        this.purpose = purpose;
        this.item = item;
    }
}
