package com.schedule.dto;

import lombok.Getter;

@Getter
public class SingUpResponseDto {

    private final Long id;

    private final String username;

    private final String email;
    //  각 변수를 Long, String 타입으로 선언
    // private 와 final 로 선언

    public SingUpResponseDto(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;


    }
    // 생성자 생성
}
