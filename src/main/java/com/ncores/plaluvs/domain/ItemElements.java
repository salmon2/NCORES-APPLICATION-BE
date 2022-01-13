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
public class ItemElements {
    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @JsonIgnore
    Item item;

    @ManyToOne
    @JoinColumn(name = "elements_id")
    @JsonIgnore
    Elements elements;

    public ItemElements(Item item, Elements elements) {
        this.item = item;
        this.elements = elements;
    }
}
