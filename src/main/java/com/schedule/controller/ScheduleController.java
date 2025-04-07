package com.schedule.controller;

import com.schedule.dto.CreatScheduleRequestDto;
import com.schedule.dto.ScheduleResponseDto;
import com.schedule.entity.Schedule;
import com.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // JSON 형태로 객체 데이터를 반환
@RequestMapping("/schedules") // 요청이 오면 Controller에서 어떠한 방식으로 처리할지 정의를 하는데, 요청을 특정 메서드와 매핑하기 위해 사용
@RequiredArgsConstructor // Lombok으로 스프링에서 의존성 주입의 방법중 하나 , 생성자 주입 없이 자동으로 설정해주는 어노테이션
public class ScheduleController {

    private final ScheduleService scheduleService; // ScheduleService 를 scheduleService로 private 으로 정의

    @PostMapping //요청 추가하기 위한 매핑
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody CreatScheduleRequestDto requestDto){
        // RequestBody에 CreatScheduleRequestDto 의 형태로 담아서 요청
        ScheduleResponseDto scheduleResponseDto = scheduleService.save(requestDto.getTitle(), requestDto.getContents(), requestDto.getUsername());
        // requestDto.getTitle(), requestDto.getContents(), requestDto.getUsername() 3 가지의 정보를 scheduleService에 저장한다.

        return new ResponseEntity<>(scheduleResponseDto,HttpStatus.CREATED);
        // ResponseEntity형태로 반환하는데, scheduleResponseDto에 맞게 반환하고, CREATED 를 반환한다.
    }

    @GetMapping //데이터를 조회하기 위한 요청, 데이터를 읽기
    public ResponseEntity<List<ScheduleResponseDto>> findAll(){
        // findAll이니깐, 모든 정보를 ScheduleResponseDto 형태로 조회한다.
        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll();
        // scheduleResponseDtoList의 형태로 scheduleService에서 처리해서 정보를 조회
        return new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);
        // scheduleResponseDtoList로 반환하고 OK 를 반환
    }


    /**
     * @PathVariable
     *  - 경로 변수를 표시하기 위해 메서드에 매개변수에 사용
     *  - 경로 변수는 중괄호 {id}로 둘러싸인 값을 나타냄
     *  - URL 경로에서 변수 값을 추출하여 매개변수에 할당
     *  - 기본적으로 경로 변수는 반드시 값을 가져야 하며, 값이 없는 경우 404 오류 발생
     *  - 주로 상세 조회, 수정 , 삭제와 같은 작업에서 리소스 식별자로 사용
     */

    @GetMapping("/{id}") // id 값에 맞는 정보를 조회
    public ResponseEntity<ScheduleResponseDto>findById(@PathVariable Long id){
        // ScheduleResponseDto의 형태로 id값에 맞는 정보를 찾아온다. 이때, id 값은 Long 의 형태로 되어있다.
        ScheduleResponseDto scheduleResponseDto = scheduleService.findById(id);
        // scheduleResponseDto 를 선언하고, scheduleService 에 있는 findById 메서드를 알맞은 id 값을 가져온다.
        return new ResponseEntity<>(scheduleResponseDto,HttpStatus.OK);
        // scheduleResponseDto형태로 반환하고, OK값도 반환한다.
    }

    @PatchMapping("/{id}") // id 값에 맞는 정보를 수정
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            // ScheduleResponseDto의 형태로 id값에 맞는 정보를 update 한다.
            @PathVariable Long id,
            // id 값은 Long의 형태
            @RequestBody CreatScheduleRequestDto requestDto
            // CreatScheduleRequestDto를 requestDto로 선언
    ){

        ScheduleResponseDto scheduleResponseDto = scheduleService.updateSchedule(id,requestDto.getTitle(), requestDto.getContents());
        //scheduleResponseDto를 선언하고, scheduleService의 매서드 updateSchedule 실행하고 ,id,requestDto.getTitle(), requestDto.getContents() 의 정보를 반환한다.
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
        // scheduleResponseDto형태로 반환하고, OK값도 반환한다
    }


    @DeleteMapping("/{id}")
    // 알맞은 id값의 정보를 삭제한다.
    public ResponseEntity<Void> delete(@PathVariable Long id){
        // Long id 값에 맞는 정보를 조회해서 삭제한다.
        scheduleService.delete(id);
        // id 값에 맞는 정보를 삭제

        return new ResponseEntity<>(HttpStatus.OK);
        // OK값을 반환
    }

}
