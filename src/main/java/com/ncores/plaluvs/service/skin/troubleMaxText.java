package com.ncores.plaluvs.service.skin;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum troubleMaxText {
    One("피부를 진정할 수 있는 트러블 전용 화장품을 바르는 것을 추천해요. 트러블 전용 제품으로 피부 진정과 수분감 있는 제품으로 보습을 피부를 케어해 보세요.", 1),
    Two("과잉 피지를 억제하고 각질을 부드럽게 케어해보세요. 스킨케어에는 번들거림이나 끈적임 없는 제품을 선택해 주세요." ,2);

    private String text;
    private int id;

    public static troubleMaxText getEnum(){
        int index = (int)(Math.random() * 5 +1);

        for (troubleMaxText value : troubleMaxText.values()) {
            if(value.getId() == index)
                return value;
        }
        return troubleMaxText.One;
    }


}
