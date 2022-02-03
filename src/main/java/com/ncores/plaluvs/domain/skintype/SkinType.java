package com.ncores.plaluvs.domain.skintype;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ncores.plaluvs.domain.SkinTypeBadElements;
import com.ncores.plaluvs.domain.SkinTypeGoodElements;
import com.ncores.plaluvs.domain.Timestamped;
import com.ncores.plaluvs.domain.skintype.skindailyStatus.SkinDailyStatus;
import com.ncores.plaluvs.domain.skintype.skindailystimulation.SkinDailyStimulation;
import com.ncores.plaluvs.domain.skintype.skintrouble.SkinTrouble;
import com.ncores.plaluvs.domain.user.User;
import com.ncores.plaluvs.exception.ErrorCode;
import com.ncores.plaluvs.exception.PlaluvsException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
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


    private Long oilIndicateScore;
    private Long dryScore;

    private Long sensitivityScore;

    private Long winkleScore;

    private Long pigmentScore;

    @Enumerated(value = EnumType.STRING)
    private Bouman bouman;


    //회원가입 후 설문조사 1번(현재 피부 상태)
    @Enumerated(value = EnumType.STRING)
    private CurrentSkinStatus currentSkinStatus;

    //회원가입 후 설문조사 2번 (현재 피부 고민), 카메라 측정 전 설문조사 3번 (오늘 피부 고민)
    @OneToMany(mappedBy = "skinType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SkinTrouble> skinTroubleList = new ArrayList<>();

    //skin/Daily/stauts 카메라 측정 전 설문조사1번
    @OneToMany(mappedBy = "skinType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SkinDailyStatus> skinDailyStatusList = new ArrayList<>();

    //skin/Daily/Stimulation 카메라 측정 전 설문조사2번
    @OneToMany(mappedBy = "skinType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SkinDailyStimulation> skinDailyStimulationList = new ArrayList<>();

    //skin/daily/sef-check 카메라 측정 전 설문조사 3번 (자기 점수)
    private Double selfScore;

    //설문지 점수
    private Long score;

    //종합 점수
    private Long totalScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


    @OneToMany(mappedBy = "skinType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SkinTypeGoodElements> skinTypeGoodElementsList = new ArrayList<>();

    @OneToMany(mappedBy = "skinType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SkinTypeBadElements> skinTypeBadElementsList = new ArrayList<>();


    public SkinType(CurrentSkinStatus questionOne, User user) {
        this.oilIndicateScore = 11L;
        this.dryScore = 5L;
        this.sensitivityScore = 7L;
        this.winkleScore = 1L;
        this.pigmentScore = 2L;
        this.bouman = Bouman.ORNT;

        this.currentSkinStatus = questionOne;
        this.user = user;
    }

    public SkinType(CurrentSkinStatus questionOne, User user, LocalDateTime localDateTime) {
        this.oilIndicateScore = 11L;
        this.dryScore = 5L;
        this.sensitivityScore = 7L;
        this.winkleScore = 1L;
        this.pigmentScore = 2L;
        this.bouman = Bouman.ORNT;
        this.setCreatedAt(localDateTime);
        this.setModifiedAt(localDateTime);
        this.currentSkinStatus = questionOne;
        this.user = user;
    }


    public SkinType(User user) {
        this.oilIndicateScore = 11L;
        this.dryScore = 5L;
        this.sensitivityScore = 7L;
        this.winkleScore = 1L;
        this.pigmentScore = 2L;

        this.user = user;
    }

}
