package com.ncores.plaluvs.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignInResponseDto {
    private String token;
    private Long userId;
    private String nickname;
    private Boolean GenderExist;
    private Boolean ageExist;
}
