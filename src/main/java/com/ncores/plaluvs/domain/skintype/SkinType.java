package com.ncores.plaluvs.domain.skintype;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ncores.plaluvs.domain.SkinTypeBadElements;
import com.ncores.plaluvs.domain.SkinTypeGoodElements;
import com.ncores.plaluvs.domain.Timestamped;
import com.ncores.plaluvs.domain.skintype.skindailyStatus.SkinDailyStatus;
import com.ncores.plaluvs.domain.skintype.skintrouble.SkinTrouble;
import com.ncores.plaluvs.domain.user.User;
import com.ncores.plaluvs.exception.ErrorCode;
import com.ncores.plaluvs.exception.PlaluvsException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class SkinType extends Timestamped {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private CurrentSkinStatus questionOne;

    @Enumerated(value = EnumType.STRING)
    private Sensitivity sensitivity;

    @Enumerated(value = EnumType.STRING)
    private Winkle winkle;

    @Enumerated(value = EnumType.STRING)
    private Pigment pigment;


    private Long oilIndicateScore;

    private Long sensitivityScore;

    private Long winkleScore;

    private Long pigmentScore;

    @Enumerated(value = EnumType.STRING)
    private Bouman bouman;

    private Double selfScore;

    private Long score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "skinType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SkinTrouble> skinTroubleList = new ArrayList<>();

    @OneToMany(mappedBy = "skinType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SkinDailyStatus> dailySkinStatusList = new ArrayList<>();

    @OneToMany(mappedBy = "skinType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SkinTypeGoodElements> skinTypeGoodElementsList = new ArrayList<>();

    @OneToMany(mappedBy = "skinType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SkinTypeBadElements> skinTypeBadElementsList = new ArrayList<>();




    public SkinType(CurrentSkinStatus questionOne, User user) {
        this.questionOne = questionOne;
        this.user = user;
    }


    public void updateSkinType(CurrentSkinStatus questionOne) {
        this.questionOne = questionOne;
    }

    public static void skinTypeCheck(SkinType skinType) throws PlaluvsException {
        if(skinType == null)
            throw new PlaluvsException(ErrorCode.DATA_EMPTY);
    }
}
