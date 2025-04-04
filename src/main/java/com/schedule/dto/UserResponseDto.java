package com.schedule.dto;

import lombok.Getter;

@Getter
public class UserResponseDto {
//    private final Long id;

    private final String username;

    private final String email;

    public UserResponseDto(String username, String email) {
//        this.id = id;
        this.username = username;
        this.email = email;
    }
}
