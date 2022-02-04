package com.ncores.plaluvs.service.skin;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum troubleMinText {
    One("현재 피부는 맑고 깨끗한 상태예요.",1);

    private String text;
    private int id;

    public troubleMinText getEnum(){
        int index = (int)(Math.random() * 3 +1);

        for (troubleMinText value : troubleMinText.values()) {
            if(value.getId() == index)
                return value;
        }
        return troubleMinText.One;
    }

}
