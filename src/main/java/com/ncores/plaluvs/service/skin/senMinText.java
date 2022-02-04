package com.ncores.plaluvs.service.skin;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum senMinText {
    One("피부가 건강한 상태에요.",1),
    Two("외부 자극에 민감하지 않은 건강한 피부네요", 2),
       Three     ("튼튼한 피부 장벽을 유지하기 위해 꾸준한 스킨케어 루틴이 필요해요.",3);

    private String text;
    private int id;

    public static senMinText getEnum(){
        int index = (int)(Math.random() * 5 +1);

        for (senMinText value : senMinText.values()) {
            if(value.getId() == index)
                return value;
        }
        return senMinText.One;
    }


}
