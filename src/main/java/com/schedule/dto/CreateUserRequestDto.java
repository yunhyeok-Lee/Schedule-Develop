package com.schedule.dto;

import lombok.Getter;

@Getter
public class CreateUserRequestDto {

    private final String username;
    // String 타입으로 uesername 선언 (private 값으로 선언) final 키워드는 한번 초기화 되면 변경 X

    private final String email;
    //String 타입으로 email 선언 (private 값으로 선언) final 키워드는 한번 초기화 되면 변경 X

    public CreateUserRequestDto(String username, String email) {
        this.username = username;
        this.email = email;

        // 생성자를 생성
    }
}
