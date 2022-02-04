package com.ncores.plaluvs.service.skin;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum pigMaxText {
    One("기미나 주근깨 등이 피부에서 보인다면 색소성 피부라고 생각할 수 있어요. 색소성 피부는 햇빛 노출에 많은 영향을 받는답니다. 때문에 자외선을 차단하는 것이 가장 중요하겠죠.",1),
    Two("일상생활에서는 SPF 15정도의 자외선 차단제가 충분하지만 장기간 야외 활동이 필요하다면 SPF 30 이상의 제품을 사용해 주세요. 2-3시간마다 다시 바르는 걸 추천해요.",2),
       Three("색소성이 높다면 외출 전에 자외선 지수를 확인하고 자외선 차단제로 꼼꼼하게 관리해 주세요.",3);

    private String text;
    private int id;

    public static pigMaxText getEnum(){
        int index = (int)(Math.random() * 5 +1);

        for (pigMaxText value : pigMaxText.values()) {
            if(value.getId() == index)
                return value;
        }
        return pigMaxText.One;
    }

}
