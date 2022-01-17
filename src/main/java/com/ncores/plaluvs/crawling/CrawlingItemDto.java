package com.ncores.plaluvs.crawling;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class CrawlingItemDto {
    private String name;
    private String image;
    private String volume;
    private String brand;
    private String discription;
    private List<String> tags;
    private List<Map<String, String>> elements = new ArrayList<>();
    private String colors;
    private Long id;
    private String price;
    private List<String> category;

    public CrawlingItemDto(Map<String, Object> map) {
        this.name = (String) map.get("name");
        this.image = (String) map.get("image");
        this.volume = (String) map.get("volume");
        this.brand = (String) map.get("brand");
        this.discription = (String) map.get("discription");
        this.colors = (String) map.get("colors");
        this.id = Long.valueOf(map.get("id").toString());
        this.category = (ArrayList<String>)map.get("category");
        this.tags = (ArrayList<String>)map.get("tags");
        this.elements = (ArrayList<Map<String, String>>)map.get("elements");
        this.price = (String) map.get("price");
    }
}
