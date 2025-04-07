package com.schedule.dto;

import com.schedule.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {

    private final Long id;

    private final String title;

    private final String contents;

    // 각 변수를 Long , String 타입으로 선언하고 private 와 final로 선언한다.

    public ScheduleResponseDto(Long id, String title, String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }

    // 생성자 생성

    public static ScheduleResponseDto toDto(Schedule schedule){
        // schedule을 Dto값으로 받는다.
        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContents());
        // id, title, contents 값을 Dto에 담아 리턴한다.
    }
}
