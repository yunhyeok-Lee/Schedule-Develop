package com.schedule.dto;

import lombok.Getter;

@Getter
public class CreatScheduleRequestDto {

        private final String title;

        private final String contents;

        private final String username;

    public CreatScheduleRequestDto(String title, String contents, String username) {
        this.title = title;
        this.contents = contents;
        this.username = username;
    }
}
