package com.schedule.dto;

import lombok.Getter;

@Getter
public class UserUpdateRequestDto {

    private final String username;

    private final String email;
    // 각 변수를 String 타입으로 선언
    // private 와 final 로 선언

    public UserUpdateRequestDto(String username, String email) {
        this.username = username;
        this.email = email;
    }
    // 생성자 생성
}
