package com.ncores.plaluvs.service.skin.text;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum dryText {
    one("피부 보습 케어뿐만 아니라 물도 자주 마셔주시면 좋아요",1),
    two("피지 분비가 적은 건성은 대체적으로 피부가 연약해요. 피부를 부드럽고 촉촉하게 가꿔 줄 스킨케어 제품을 사용해 보세요",2),
    three("피부 표면에 각질이 일어났다면 스크럽을 통해 피부를 화사하게 가꿔보세요",3);

    private String text;
    private int id;

    public static dryText getEnum(){
        int index = (int)(Math.random() * 3 +1);

        for (dryText value : dryText.values()) {
            if(value.getId() == index)
                return value;
        }
        return dryText.one;
    }

    public static dryText getEnum(Long id) {
        for (dryText value : dryText.values()) {
            if (value.getId() == id)
                return value;
        }
        return dryText.one;
    }

}
