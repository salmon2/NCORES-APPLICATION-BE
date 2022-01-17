package com.ncores.plaluvs.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    private Long id;

    private String categoryLarge;

    private String categoryMiddle;

    private String categorySmall;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Cosmetic> cosmeticList = new ArrayList<>();

    public Category(String categoryLarge) {
        this.categoryLarge = categoryLarge;
    }

    public Category(String categoryLarge, String categoryMiddle) {
        this.categoryLarge = categoryLarge;
        this.categoryMiddle = categoryMiddle;
    }

    public Category(String categoryLarge, String categoryMiddle, String categorySmall) {
        this.categoryLarge = categoryLarge;
        this.categoryMiddle = categoryMiddle;
        this.categorySmall = categorySmall;
    }

    public static Category createCategoryByCrawling(List<String> category){
        Category resultCategory = null;
        if(category.size() == 1)
            resultCategory = new Category(category.get(0));
        else if(category.size() == 2)
            resultCategory = new Category(category.get(0), category.get(1));
        else if(category.size() == 3)
            resultCategory = new Category(category.get(0), category.get(1), category.get(2));

        return resultCategory;
    }
}
