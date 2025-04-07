package com.schedule.dto;

import lombok.Getter;

@Getter
public class CreatScheduleRequestDto {

        private final String title;
        // title 를  String 타입으로 선언하고 private 와 final으로 선언

        private final String contents;
        // contents 를  String 타입으로 선언하고 private 와 final으로 선언

        private final String username;
         // username 를  String 타입으로 선언하고 private 와 final으로 선언
    public CreatScheduleRequestDto(String title, String contents, String username) {
        this.title = title;
        this.contents = contents;
        this.username = username;

        // 생성자 생성
    }
}
