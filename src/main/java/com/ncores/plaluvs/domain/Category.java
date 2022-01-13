package com.ncores.plaluvs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryLarge;

    private String categoryMiddle;

    private String categorySmall;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Item> itemList = new ArrayList<>();

    public Category(String categoryLarge) {
        this.categoryLarge = categoryLarge;
    }

    public Category(List<String> category) {
        if(category.size() == 1)
            this.categoryLarge = category.get(0);
        else if(category.size() == 2) {
            this.categoryLarge = category.get(0);
            this.categoryMiddle = category.get(1);
        }
        else if(category.size() == 3) {
            this.categoryLarge = category.get(0);
            this.categoryMiddle = category.get(1);
            this.categorySmall = category.get(2);
        }
    }
}
