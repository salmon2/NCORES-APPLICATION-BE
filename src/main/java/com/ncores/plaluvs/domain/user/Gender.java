package com.ncores.plaluvs.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender {
    MALE("Male"),
    FEMALE("Female");

    private String text;

    public static Gender findGender(String gender){
        for (Gender value : Gender.values()) {
            if(value.getText().equals(gender)){
                return value;
            }
        }
        return null;
    }
}
