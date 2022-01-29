package com.ncores.plaluvs.controller.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KaKaoRequestDto {
    private String email;
    private String nickname;
    private String password;
}
