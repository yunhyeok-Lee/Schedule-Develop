package com.schedule.controller;


import com.schedule.dto.*;
import com.schedule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @PostMapping
//    public ResponseEntity<UserResponseDto> save(@RequestBody CreateUserRequestDto createUserRequestDto){
//
//        UserResponseDto userResponseDto = userService.save(createUserRequestDto.getUsername(), createUserRequestDto.getEmail());
//
//        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
//
//    }

    @PostMapping("/signup")
    public ResponseEntity<SingUpResponseDto> singUp(@RequestBody SingUpRequestDto singUpRequestDto){

        SingUpResponseDto singUpResponseDto = userService.signUp(singUpRequestDto.getUsername(), singUpRequestDto.getEmail(), singUpRequestDto.getPassword());

        return new ResponseEntity<>(singUpResponseDto ,HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> fingById(@PathVariable Long id){

        UserResponseDto userResponseDto = userService.findById(id);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> userUpdate(@PathVariable Long id, @RequestBody UserUpdateRequestDto userUpdateRequestDto){


        userService.userUpdate(id,userUpdateRequestDto.getUsername(),userUpdateRequestDto.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
