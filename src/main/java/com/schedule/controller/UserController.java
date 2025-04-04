package com.schedule.controller;


import com.schedule.dto.CreateUserRequestDto;
import com.schedule.dto.SingUpRequestDto;
import com.schedule.dto.SingUpResponseDto;
import com.schedule.dto.UserResponseDto;
import com.schedule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> save(@RequestBody CreateUserRequestDto createUserRequestDto){

        UserResponseDto userResponseDto = userService.save(createUserRequestDto.getUsername(), createUserRequestDto.getEmail());

        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);

    }

    @PostMapping
    public ResponseEntity<SingUpResponseDto> singUp(@RequestBody SingUpRequestDto singUpRequestDto){

        SingUpResponseDto singUpResponseDto = userService.signUp(singUpRequestDto.getUsername(), singUpRequestDto.getEmail(), singUpRequestDto.getPassword());

        return new ResponseEntity<>(singUpResponseDto ,HttpStatus.CREATED);
    }


}
