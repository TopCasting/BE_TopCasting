package com.ll.topcastingbe.global.exception.member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum UserErrorMessage {

    USER_AND_WRITER_NOT_MATCH("수정 권한이 없습니다."),
    USER_NOT_FOUND("해당 사용자를 찾을 수 없습니다."),
    USERNAME_ALREADY_EXISTS("중복되는 아이디가 존재합니다."),
    NICKNAME_ALREADY_EXISTS("중복되는 닉네임이 존재합니다."),
    PASSWORD_AND_PASSWORD_CHECK_NOT_MATCH("패스워드를 다시 한번 확인해주세요"),
    PASSWORD_NOT_MATCH("패스워드가 일치하지 않습니다."),
    INVALID_CREDENTIALS("아이디, 비밀번호를 다시 확인해주세요");


    private final String message;
}
