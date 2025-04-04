package com.schedule.dto;

import lombok.Getter;

@Getter
public class CreateUserRequestDto {

    private final String username;

    private final String email;

    public CreateUserRequestDto(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
