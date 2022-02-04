package com.ncores.plaluvs.service.skin;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ageMaxText {
    One("40대 피부에는 고기능성 화장품을 선택하는 것이 중요해요. 영양크림을 얼굴 전체에 발라주는 것만으로도 피부에 도움을 준답니다.",1),
    Two("저녁에는 수분 공급을 위해서 나이트 크림을 추천해요. 콜라겐, 히알루론산 등 노화를 늦추는 성분을 함유한 제품이 좋아요.",2),
       Three     ("비타민이나 재생 효과가 있는 세럼을 추천해요. 크림 전 단계에서 발라주면 효과적이랍니다.",3);
    private String text;
    private int id;

    public static ageMaxText getEnum(){
        int index = (int)(Math.random() * 3 +1);

        for (ageMaxText value : ageMaxText.values()) {
            if(value.getId() == index)
                return value;
        }
        return ageMaxText.One;
    }
}
