package com.ncores.plaluvs.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ncores.plaluvs.crawling.CrawlingItemDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue
    private Long id;

    private Long crawlingId;

    @Column
    private String itemName;

    @Column(columnDefinition = "text")
    private String itemImg;

    @Column
    private String itemBrand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category categoryId;

    @Column(columnDefinition = "text")
    private String itemColors;

    @Column
    private String itemVolume;

    @Column(columnDefinition = "text")
    private String itemDescription;

    @Column(columnDefinition = "INT DEFAULT 0")
    private int dibsCnt;

    public Item(CrawlingItemDto crawlingItemDto, Category category) {
        this.crawlingId = crawlingItemDto.getId();
        this.itemName = crawlingItemDto.getName();
        this.itemImg = crawlingItemDto.getImage();
        this.itemBrand = crawlingItemDto.getBrand();
        this.categoryId = category;
        this.itemColors = crawlingItemDto.getColors();
        this.itemVolume = crawlingItemDto.getVolume();
        this.itemDescription = crawlingItemDto.getDiscription();
    }
}
