package com.ncores.plaluvs.service.skin.text;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum troubleMiddleText {
    One("트러블로 민감해진 피부를 손으로 만지지 않도록 신경 써 주세요. 세안 시에는 미지근한 물을 사용해서 꼼꼼하게 클렌징해주세요.",1),
    Two("트러블이 잘 발생하는 피부에는 진정 또는 항염 효과가 있는 스팟 제품으로 케어해보세요.", 2);

    private String text;
    private int id;

    public  static troubleMiddleText getEnum(){
        int index = (int)(Math.random() * 5 +1);

        for (troubleMiddleText value : troubleMiddleText.values()) {
            if(value.getId() == index)
                return value;
        }
        return troubleMiddleText.One;
    }

    public static troubleMiddleText getEnum(Long id) {
        for (troubleMiddleText value : troubleMiddleText.values()) {
            if (value.getId() == id)
                return value;
        }
        return troubleMiddleText.One;
    }



}
