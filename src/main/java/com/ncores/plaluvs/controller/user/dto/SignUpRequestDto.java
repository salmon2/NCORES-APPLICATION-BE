package com.ncores.plaluvs.controller.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto {
    private String email;
    private String password;
    private String confirmPassword;
    private String nickname;
}
