package com.ncores.plaluvs.service.skin.text;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum troubleMinText {
    One("현재 피부는 맑고 깨끗한 상태예요.",1);

    private String text;
    private int id;

    public static troubleMinText getEnum(){
        int index = (int)(Math.random() * 3 +1);

        for (troubleMinText value : troubleMinText.values()) {
            if(value.getId() == index)
                return value;
        }
        return troubleMinText.One;
    }

    public static troubleMinText getEnum(Long id) {
        for (troubleMinText value : troubleMinText.values()) {
            if (value.getId() == id)
                return value;
        }
        return troubleMinText.One;
    }

}
