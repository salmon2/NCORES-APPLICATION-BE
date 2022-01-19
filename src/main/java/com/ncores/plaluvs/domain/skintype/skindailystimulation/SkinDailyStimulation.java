package com.ncores.plaluvs.domain.skintype.skindailystimulation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ncores.plaluvs.domain.skintype.SkinType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SkinDailyStimulation {

    @Id @GeneratedValue
    Long id;


    @Enumerated(EnumType.STRING)
    SkinDailyStimulationEnum skinDaily;



    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "skin_type_id")
    @JsonIgnore
    private SkinType skinType;

    public SkinDailyStimulation(SkinDailyStimulationEnum skinDaily, SkinType skinType) {
        this.skinDaily = skinDaily;
        this.skinType = skinType;
    }
}
