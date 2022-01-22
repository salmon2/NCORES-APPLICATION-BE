package com.ncores.plaluvs.controller.skin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SkinStatusListResponseDto {
    private List<StatusList> statusList;
    private String content1;
    private String content2;



}
