package com.ncores.plaluvs.service.skin.text;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum commonText {
    One("수분과 보습은 항상 유지해 주세요!",1),
    Two("자외선 차단제는 잊지 말고 발라주세요!",2),
       Three     ("보통 피부는 밤 10~11시부터 새벽 2~3시까지 재생이 이루어지니 오늘은 일찍 잠을 청해보세요.",3),
            Four("피부 외에도 쉽게 건조해지는 눈가와 입술은 따로 관리해 주세요.",4),
            Five("피부가 좋아지는 방법의 가장 간단한 방법 항상 피부를 촉촉하게 유지하는 것이랍니다. 세안 후 보습제를 꼭 발라주세요.",5),
            Six("햇볕은 피부의 조기 노화의 원인이 될 수 있답니다. 흐린 날에도 꼭 자외선 차단제를 바르시는 걸 추천드려요!",6);

    private String text;
    private int id;

    public commonText getEnum(){
        int index = (int)(Math.random() * 3 +1);

        for (commonText value : commonText.values()) {
            if(value.getId() == index)
                return value;
        }
        return commonText.One;
    }
}
