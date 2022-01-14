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

    @Column(columnDefinition = "text")
    private String itemColors;

    @Column
    private String itemVolume;

    @Column(columnDefinition = "text")
    private String itemDescription;

    @Column(columnDefinition = "INT DEFAULT 0")
    private int dibsCnt;

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserItem> userItemList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ItemElements> itemElementsList = new ArrayList<>();


    public Item(CrawlingItemDto crawlingItemDto, Category category) {
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
