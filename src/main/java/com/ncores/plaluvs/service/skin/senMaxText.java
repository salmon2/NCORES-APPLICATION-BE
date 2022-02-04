package com.ncores.plaluvs.service.skin;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum senMaxText {
    One("피부가 민감해졌다고 느낀다면 성분을 꼼꼼히 보고 최소한의 성분이 들어있는 보습제만으로 피부를 진정시켜 보세요. 피부 장벽이 튼튼할수록 건강한 피부를 갖게 될 거예요.",1),
    Two("꼭 필요한 화장품만 골라서 스킨케어 단계를 줄여보세요. 플라럽스에서 나에게 맞는 상품이나 성분을 찜 해놓고 관리할 수 있답니다.",2),
    Three("기능성 화장품보다는 보습과 진정에 집중해서 피부 장벽을 회복시키는 것이 중요해요.",3);

    private String text;
    private int id;

    public static senMaxText getEnum(){
        int index = (int)(Math.random() * 5 +1);

        for (senMaxText value : senMaxText.values()) {
            if(value.getId() == index)
                return value;
        }
        return senMaxText.One;
    }

}
