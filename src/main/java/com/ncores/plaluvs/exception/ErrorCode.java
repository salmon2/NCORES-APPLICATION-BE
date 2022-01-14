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
    PASSWORD_FAIL(HttpStatus.FORBIDDEN, "적합하지 않는 비밀번호입니다."),

    USER_NOT_FOUND(HttpStatus.FORBIDDEN, "유저를 찾을 수 없습니다."),
    USER_NOT_LOGIN(HttpStatus.UNAUTHORIZED, "로그인 상태가 아닙니다."),

    DATA_EMPTY(HttpStatus.FORBIDDEN, "데이터가 없습니다."),
    GENDER_NOT_EXIST(HttpStatus.CONFLICT, "적합한 성별 유형이 아닙니다."),

    AGE_TYPE_NOT_SUITABLE(HttpStatus.FORBIDDEN , "적합한 년도가 아닙니다.");


    private final HttpStatus httpStatus;
    private final String message;
}
