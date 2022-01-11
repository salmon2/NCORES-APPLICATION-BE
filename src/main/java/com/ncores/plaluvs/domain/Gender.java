package com.ncores.plaluvs.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender {
    Male("Male"),
    Female("Female");

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
