package com.ncores.plaluvs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ncores.plaluvs.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BookmarkElements {
    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "elements_id")
    Elements elements;


}
