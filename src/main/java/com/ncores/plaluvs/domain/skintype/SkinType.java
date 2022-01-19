package com.ncores.plaluvs.domain.skintype;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ncores.plaluvs.domain.SkinTypeBadElements;
import com.ncores.plaluvs.domain.SkinTypeGoodElements;
import com.ncores.plaluvs.domain.Timestamped;
import com.ncores.plaluvs.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SkinType extends Timestamped {
    @Id
    @GeneratedValue
    private Long id;


    private Long oilIndicate;

    private Long sensitivity;

    private Long winkle;

    private Long pigment;

    @Enumerated(value = EnumType.STRING)
    private Bouman bouman;

    private Long score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "skinType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SkinTypeGoodElements> skinTypeGoodElementsList = new ArrayList<>();

    @OneToMany(mappedBy = "skinType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SkinTypeBadElements> skinTypeBadElementsList = new ArrayList<>();


    public SkinType(OilIndicate oilIndicate, User user) {
        this.oilIndicate = oilIndicate;
        this.user = user;
    }


    public void updateSkinType(OilIndicate oilIndicate) {
        this.oilIndicate = oilIndicate;
    }
}
