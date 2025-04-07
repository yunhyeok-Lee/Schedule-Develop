package com.schedule.dto;


import lombok.Getter;

@Getter
public class SingUpRequestDto {

    private final String username;

    private final String email;

    private final String password;
    // 각 변수를 String 타입으로 선언
    // private 와 final 로 선언

    public SingUpRequestDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    // 생성자 생성
}
