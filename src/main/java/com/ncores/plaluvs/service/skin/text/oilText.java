package com.ncores.plaluvs.service.skin.text;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum oilText {
    One("종종 넓은 모공과 번들거림이 보이는 피부예요. 적절한 피부 균형을 맞추는 게 중요해요.",1),
    Two("가벼우면서도 수분 공급을 해줄 수 있는 제품을 추천해요.",2),
    Three("매일매일 강력한 딥 클렌징보다는 피부에 적당한 산뜻함을 줄 수 있는 세안 제품을 세안 루틴에 맞춰 사용해 보세요.", 3),
    Four("피부 속 균형을 맞춰 줄 수분 가득한 제품을 선택해 보세요.", 4),
    Five("팩을 할 때 여드름이나 트러블이 있는 민감한 부위는 피해주세요.", 5);

    private String text;
    private int id;

    public static  oilText getEnum(){
        int index = (int)(Math.random() * 5 +1);

        for (oilText value : oilText.values()) {
            if(value.getId() == index)
                return value;
        }
        return oilText.One;
    }

    public static oilText getEnum(Long id) {
        for (oilText value : oilText.values()) {
            if (value.getId() == id)
                return value;
        }
        return oilText.One;
    }



}
