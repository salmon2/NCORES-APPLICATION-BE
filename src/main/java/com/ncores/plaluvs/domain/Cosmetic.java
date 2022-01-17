package com.ncores.plaluvs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ncores.plaluvs.crawling.CrawlingItemDto;
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
public class Cosmetic {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long crawlingId;

    @Column
    private String itemName;

    @Column(columnDefinition = "text")
    private String itemImg;

    @Column
    private String itemBrand;

    @Column(columnDefinition = "text")
    private String itemColors;

    @Column
    private String itemVolume;

    @Column(columnDefinition = "text")
    private String itemDescription;

    private String price;

    @Column(columnDefinition = "INT DEFAULT 0")
    private int dibsCnt;

    @OneToMany(mappedBy = "cosmetic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserCosmetic> userCosmeticList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    @OneToMany(mappedBy = "cosmetic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CosmeticElements> cosmeticElementsList = new ArrayList<>();


    public Cosmetic(CrawlingItemDto crawlingItemDto, Category category) {
        this.price = crawlingItemDto.getPrice();
        this.crawlingId = crawlingItemDto.getId();
        this.itemName = crawlingItemDto.getName();
        this.itemImg = crawlingItemDto.getImage();
        this.itemBrand = crawlingItemDto.getBrand();
        this.category = category;
        this.itemColors = crawlingItemDto.getColors();
        this.itemVolume = crawlingItemDto.getVolume();
        this.itemDescription = crawlingItemDto.getDiscription();
    }
}
