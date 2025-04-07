package com.schedule.service;

import com.schedule.dto.ScheduleResponseDto;
import com.schedule.entity.Schedule;
import com.schedule.entity.User;
import com.schedule.repository.ScheduleRepository;
import com.schedule.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // Lombok으로 스프링에서 의존성 주입의 방법중 하나 , 생성자 주입 없이 자동으로 설정해주는 어노테이션
public class ScheduleService {

    private final UserRepository userRepository;
    // userRepository 를 private final 의 형태로 선언
    private final ScheduleRepository scheduleRepository;
    // scheduleRepository 를 private final의 형태로 선언

    public ScheduleResponseDto save(String title, String contents, String username) {
        // save에는 title, contents, username이  String 형태로 있다.
        User findUser = userRepository.findUserByUsersnameOrElseThrow (username);
        // username으로 유저를 찾아서 findUser에 저장하고 없으면 예외를 던진다.
        Schedule schedule = new Schedule(title, contents);
        schedule.setUser(findUser);

        Schedule savedSchedule = scheduleRepository.save(schedule);
        // scheduleRepository의 save 매서드 실행

        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContents());
        // ScheduleResponseDto형태로 반환. (id, title, contents)
    }

    public List<ScheduleResponseDto> findAll() {
        // List에 Dto형태로 findAll을 한다
        scheduleRepository.findAll()
                // DB에서 모든 일정 엔티티를 가져온다.
                .stream()
                // 리스트를 스트림 형태로 바꿈
                .map(ScheduleResponseDto::toDto)
                //일정 객체를 Dto로 변환
                .toList(); // List Dto로 변환
        return null;
    }

    public ScheduleResponseDto findById(Long id) {
        // Long id 값을 Dto 형태로 find 한다.
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        // Schedule으로 일정을 찾아서 저장하고 없으면 예외를 던진다.
        User writer = findSchedule.getUser();
        return new ScheduleResponseDto(findSchedule.getId(), findSchedule.getTitle(), findSchedule.getContents());
        // id, title, contents 를 Dto 형태로 반환
    }


    public void delete(Long id) {
        // Long id 값을 삭제
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        // findSchedule으로 일정을 찾고 없으면 예외를 던진다.
        scheduleRepository.delete(findSchedule);
        // scheduleRepository에 있는 찾은 일정값을 삭제
    }

    @Transactional
    /**
     * 트랜잭션을 관리해주는 어노테이션
     * 하나의 작업 단위를 묶어서 처리하고, 중간에 실패하면 전체를 롤백
     * 즉, 모두 성공하거나, 전부 실패하게 만듬.
     */
    public ScheduleResponseDto updateSchedule(Long id, String title, String contents) {
        // id , title, contents 를 Dto 값으로 받는다
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        // schedule 를 찾아서 저장하고 없으면 예외를 던짐.
        findSchedule.updateTitle(title);
        // 찾은 일정의 타이틀을 업데이트(수정)
        findSchedule.updateContents(contents);
        // 찾은 일정의 내용을 업데이트(수정)

        return ScheduleResponseDto.toDto(findSchedule);
        // 수정한 값을 Dto 형태로 반환
    }
}
