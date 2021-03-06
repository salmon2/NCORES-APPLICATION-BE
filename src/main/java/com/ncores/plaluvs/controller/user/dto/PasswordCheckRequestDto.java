package com.ncores.plaluvs.controller.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordCheckRequestDto {
    private String password;
    private String confirmPassword;
}
