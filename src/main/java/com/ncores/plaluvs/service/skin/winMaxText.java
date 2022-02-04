package com.ncores.plaluvs.service.skin;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum winMaxText {
    One("시간이 지날수록 이마와 눈가, 팔자주름이 신경 쓰인다면 수시로 수분감이 풍부한 크림을 바르는 것이 도움이 돼요.",1),
    Two("잔주름이 눈에 보인다면 페이셜 오일을 크림에 섞어 발라주거나 오일을 소량 덜어 스킨케어 마지막 단계에 코팅하듯 얇게 덮어주세요.", 2),
       Three     ("뜨거운 물은 얼굴의 수분을 앗아가서 주름을 생기게 할 수 있어요. 미지근한 물로 세안 또는 샤워 후 바로 보습 제품을 사용해 주세요.", 3),
            Four("리프팅 케어 제품을 흡수시키면서 가벼운 마사지를 함께 해보세요. 탄력 케어뿐만 아니라 스트레스 해소까지 된답니다.", 4);



    private String text;
    private int id;

    public static winMaxText getEnum(){
        int index = (int)(Math.random() * 5 +1);

        for (winMaxText value : winMaxText.values()) {
            if(value.getId() == index)
                return value;
        }
        return winMaxText.One;
    }



}
