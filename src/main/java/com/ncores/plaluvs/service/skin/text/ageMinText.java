package com.ncores.plaluvs.service.skin.text;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ageMinText {
    One("20대 후반으로 접어들면 작은 주름이나 모공이 보일 수 있어요.",1),
    Two("속부터 반짝이는 피부를 갖기 위해서는 스트레스를 줄이고 충분한 잠과 휴식이 필요하답니다.",2),
    Three("맑게 빛나는 피부를 위해 주 1회-2회 부드럽게 각질 제거를 해주세요. 각질 관리 후에는 보습 크림을 평소에 2배 정도 발라주세요.",3),
    Four("20대 후반부터는 탄력 케어를 함께 신경 써주면 좋아요.",4);

    private String text;
    private int id;

    public static ageMinText getEnum(){
        int index = (int)(Math.random() * 3 +1);

        for (ageMinText value : ageMinText.values()) {
            if(value.getId() == index)
                return value;
        }
        return ageMinText.One;
    }

    public static ageMinText getEnum(Long id){
        for (ageMinText value : ageMinText.values()) {
            if(value.getId() == id)
                return value;
        }
        return ageMinText.One;
    }


}
