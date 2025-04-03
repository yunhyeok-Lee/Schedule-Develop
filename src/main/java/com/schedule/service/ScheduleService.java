package com.schedule.service;

import com.schedule.dto.ScheduleResponseDto;
import com.schedule.entity.Schedule;
import com.schedule.entity.User;
import com.schedule.repository.ScheduleRepository;
import com.schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;


    public ScheduleResponseDto save(String title, String contents, String username) {

        User findUser = userRepository.findUsersnameOrElseThrow(username);

        Schedule schedule = new Schedule(title, contents);
        schedule.setUser(findUser);

        Schedule savedSchedule = scheduleRepository.save(schedule);


        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContents());
    }

    public List<ScheduleResponseDto> findAll() {

        scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();
        return null;
    }
}
