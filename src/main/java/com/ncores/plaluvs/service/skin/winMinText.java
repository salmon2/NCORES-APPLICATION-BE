package com.ncores.plaluvs.service.skin;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum winMinText {
    One("탄탄한 피부를 가지고 있어요.",1),
    Two("탄력 있는 피부를 가지고 있어요. 적절한 운동과 가벼운 셀프 마사지는 지금과 같은 피부 상태를 유지시키는데 도움이 될 거예요.", 2);

    private String text;
    private int id;

    public static winMinText getEnum(){
        int index = (int)(Math.random() * 5 +1);

        for (winMinText value : winMinText.values()) {
            if(value.getId() == index)
                return value;
        }
        return winMinText.One;
    }


}
