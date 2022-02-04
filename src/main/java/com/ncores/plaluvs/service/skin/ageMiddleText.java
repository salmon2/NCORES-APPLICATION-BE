package com.ncores.plaluvs.service.skin;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ageMiddleText {
    One("30대의 피부는 피부 컨디션의 변화가 자주 있는 피부입니다. 건조하지 않도록 잘 관리 해 주는 것이 중요해요.",1),
    Two("속부터 반짝이는 피부를 갖기 위해서는 스트레스를 줄이고 충분한 잠과 휴식이 필요하답니다.",2),
       Three     ("30대 피부는 피부 결 케어와 탄력 케어가 중요해요.",3);
    private String text;
    private int id;

    public static ageMiddleText getEnum(){
        int index = (int)(Math.random() * 3 +1);

        for (ageMiddleText value : ageMiddleText.values()) {
            if(value.getId() == index)
                return value;
        }
        return ageMiddleText.One;
    }


}
