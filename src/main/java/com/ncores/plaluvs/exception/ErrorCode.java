package com.ncores.plaluvs.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USERNAME_EMPTY(HttpStatus.FORBIDDEN, "Email을 입력해주세요."),
    USERNAME_DUPLICATE(HttpStatus.CONFLICT, "중복된 Email 입니다."),

    NICKNAME_EMPTY(HttpStatus.FORBIDDEN, "Nickname을 입력해주세요"),
    NICKNAME_DUPLICATE(HttpStatus.CONFLICT, "중복된 NIckName 입니다."),

    PASSWORD_EMPTY(HttpStatus.FORBIDDEN, "비밀번호를 입력해주세요."),
    PASSWORD_CHECK_EMPTY(HttpStatus.FORBIDDEN, "비밀번호 확인을 입력해주세요"),

    PASSWORD_NOT_EQUAL(HttpStatus.FORBIDDEN, "비밀번호와 비밀번화 확인과 일치하지 않습니다."),
    PASSWORD_FAIL(HttpStatus.FORBIDDEN, "비밀번호는 8자 이상 필요합니다."),
    PASSWORD_NOT_EQAUL_DELETE(HttpStatus.FORBIDDEN, "비밀번호가 일치하지 않습니다."),

    USER_NOT_FOUND(HttpStatus.FORBIDDEN, "유저를 찾을 수 없습니다."),
    USER_NOT_LOGIN(HttpStatus.UNAUTHORIZED, "로그인 상태가 아닙니다."),

    DATA_EMPTY(HttpStatus.FORBIDDEN, "데이터가 없습니다."),
    DATA_NOT_TYPE(HttpStatus.FORBIDDEN, "적합한 유형의 데이터가 아닙니다."),
    GENDER_NOT_EXIST(HttpStatus.CONFLICT, "적합한 성별 유형이 아닙니다."),

    AGE_TYPE_NOT_SUITABLE(HttpStatus.FORBIDDEN , "적합한 년도가 아닙니다."),
    USERNAME_LENGTH_MIN(HttpStatus.FORBIDDEN, "이메일은 8자 이상 필요합니다."),
    NICKNAME_LENGTH_MAX(HttpStatus.FORBIDDEN, "닉네임 길이가 8자 미만만 가능합니다."),
    PAGE_OUT(HttpStatus.FORBIDDEN, "페이지가 최대페이지 이상입니다."),
    ELEMENT_NOT_FOUND(HttpStatus.FORBIDDEN, "해당하는 성분이 없습니다."),
    BOUMAN_NOT_FOUND(HttpStatus.FORBIDDEN, "해당하는 바우만 타입이 없습니다."),
    SKIN_TYPE_NOT_FOUND(HttpStatus.FORBIDDEN, "금일 진행한 스킨타입이 없습니다. 설문조사를 해주세요"),
    SKIN_WORRY_EMPTY(HttpStatus.FORBIDDEN, "피부 걱정 설문조사를 해주세요"),
    CATEGORY_NOT_FOUND(HttpStatus.FORBIDDEN, "해당하는 카테고리가 없습니다." ),
    COSMETIC_NOT_FOUND(HttpStatus.FORBIDDEN, "해당하는 화장품이 없습니다." ),
    PHOTO_FACE_EMPTY(HttpStatus.FORBIDDEN, "사진에 얼굴이 존재하지않습니다."), PHOTO_FACE_MANY(HttpStatus.FORBIDDEN, "사진에 얼굴이 너무 많습니다.");



    private final HttpStatus httpStatus;
    private final String message;
}
