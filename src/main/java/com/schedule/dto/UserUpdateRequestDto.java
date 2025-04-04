package com.schedule.dto;

import lombok.Getter;

@Getter
public class UserUpdateRequestDto {

    private final String username;

    private final String email;

    public UserUpdateRequestDto(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
