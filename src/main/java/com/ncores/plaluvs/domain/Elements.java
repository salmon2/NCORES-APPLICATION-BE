package com.ncores.plaluvs.domain;

import com.ncores.plaluvs.domain.skintype.skintrouble.SkinTroubleElements;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Elements {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String level;
    @Column(length = 100000)
    private String korean;
    @Column(length = 100000)
    private String english;
    @Column(length = 100000)
    private String purpose;

    @OneToMany(mappedBy = "elements", fetch = LAZY, cascade = CascadeType.ALL)
    List<CosmeticElements> cosmeticElementsList = new ArrayList<>();

    @OneToMany(mappedBy = "elements", fetch = LAZY, cascade = CascadeType.ALL)
    List<SkinTypeGoodElements> skinTypeGoodElementsList = new ArrayList<>();

    @OneToMany(mappedBy = "elements", fetch = LAZY, cascade = CascadeType.ALL)
    List<SkinTypeBadElements> skinTypeBadElementsList = new ArrayList<>();

    @OneToMany(mappedBy = "elements", fetch = LAZY, cascade = CascadeType.ALL)
    List<SkinTroubleElements> skinTroubleElements = new ArrayList<>();






    public Elements(Map<String, String> value) {
        this.level = value.get("level");
        this.korean = value.get("korean");
        this.english = value.get("english");
        this.purpose = value.get("purpose");
    }
}
