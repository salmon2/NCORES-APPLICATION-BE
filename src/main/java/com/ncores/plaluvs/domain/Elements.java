package com.ncores.plaluvs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ncores.plaluvs.crawling.CrawlingItemDto;
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
    @Id @GeneratedValue
    private Long id;

    private String level;
    private String korean;
    private String english;
    private String purpose;

    @OneToMany(mappedBy = "elements", fetch = LAZY, cascade = CascadeType.ALL)
    List<ItemElements> itemElementsList = new ArrayList<>();


    public Elements(Map<String, String> value) {
        this.level = value.get("level");
        this.korean = value.get("korean");
        this.english = value.get("english");
        this.purpose = value.get("purpose");
    }
}
