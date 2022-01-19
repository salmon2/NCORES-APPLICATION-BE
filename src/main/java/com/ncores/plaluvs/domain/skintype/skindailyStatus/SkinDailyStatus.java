package com.ncores.plaluvs.domain.skintype.skindailyStatus;

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
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class SkinDailyStatus {

    @Id @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private SkinDailyStatusEnum skinDaily;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "skin_type_id")
    @JsonIgnore
    private SkinType skinType;

    public SkinDailyStatus(SkinDailyStatusEnum skinDaily, SkinType skinType) {
        this.skinDaily = skinDaily;
        this.skinType = skinType;
    }
}
