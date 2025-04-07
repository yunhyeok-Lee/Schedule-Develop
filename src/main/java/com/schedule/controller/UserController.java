package com.schedule.controller;


import com.schedule.dto.*;
import com.schedule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // JSON 형태로 객체 데이터를 반환
@RequestMapping("/users") // 요청이 오면 Controller에서 어떠한 방식으로 처리할지 정의를 하는데, 요청을 특정 메서드와 매핑하기 위해 사용
@RequiredArgsConstructor // Lombok으로 스프링에서 의존성 주입의 방법중 하나 , 생성자 주입 없이 자동으로 설정해주는 어노테이션
public class UserController {

    private final UserService userService;
    // userService 선언한다. 이때 private 로 선언
    @PostMapping // 요청을 추가하기 위한 매핑
    public ResponseEntity<CreateUserRequestDto> save(@RequestBody CreateUserRequestDto createUserRequestDto){
        //CreateUserRequestDto 의 형태로 요청
        CreateUserRequestDto userResponseDto = userService.save(createUserRequestDto.getUsername(), createUserRequestDto.getEmail());
        // userResponseDto 를 선언하고 save 메서드를 실행 하는데 createUserRequestDto.getUsername(), createUserRequestDto.getEmail()의 형태로 실행
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
        // userResponseDto로 반환받고 CREATED를 반환
    }

    @PostMapping("/signup")
    // 데이터 추가, signup값 추가
    public ResponseEntity<SingUpResponseDto> singUp(@RequestBody SingUpRequestDto singUpRequestDto){
        // @RequestBody에 담아서 SingUpResponseDto형태로 signUp
        SingUpResponseDto singUpResponseDto = userService.signUp(singUpRequestDto.getUsername(), singUpRequestDto.getEmail(), singUpRequestDto.getPassword());
        //signUp 메서드를 실행하는데 메서드에는 singUpRequestDto.getUsername(), singUpRequestDto.getEmail(), singUpRequestDto.getPassword() 이러한 데이터를 담고 있고 Dto 형태로 반환
        return new ResponseEntity<>(singUpResponseDto ,HttpStatus.CREATED);
        // singUpResponseDto를 반환하고 CREATED를 반환
    }


    @GetMapping("/{id}")
    // 데이터를 조회
    public ResponseEntity<UserResponseDto> fingById(@PathVariable Long id){
        // 요청받은 id 값에 맞는 정보를 Dto 형태로 조회
        UserResponseDto userResponseDto = userService.findById(id);
        // Dto를 선언하고, 메서드를 실행하는데 알맞은 id값의 정보를 조회
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
        // userResponseDto의 형태로 반환받고, OK 를 반환
    }

    @PatchMapping("/{id}")
    // 요청받은 id 값에 맞는 정보를 수정한다.
    public ResponseEntity<UserResponseDto> userUpdate(@PathVariable Long id, @RequestBody UserUpdateRequestDto userUpdateRequestDto){
        // UserResponseDto 형태로  id 값에 맞는 정보를 수정한다.
        userService.userUpdate(id,userUpdateRequestDto.getUsername(),userUpdateRequestDto.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
        // OK 값을 리턴받는다.
    }

    @DeleteMapping("/{id}")
    // 요청받은 id 값을 삭제한다.
    public ResponseEntity<Void> delete(@PathVariable Long id){
        // 요청받은 id 값을 삭제
        userService.delete(id);
        // 메서드를 실행해 id 값에 맞는 정보를 삭제

        return new ResponseEntity<>(HttpStatus.OK);
        // 삭제 완료되면 OK 값을 리턴받는다.
    }

}
