package com.schedule.service;

import com.schedule.dto.SingUpResponseDto;
import com.schedule.dto.UserResponseDto;
import com.schedule.entity.User;
import com.schedule.repository.ScheduleRepository;
import com.schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
//
//    public UserResponseDto save(String username, String email) {
//
//        User user = new User(username, email);
//
//
//        return null;
//    }


    public SingUpResponseDto signUp(String username, String email,String password) {

        User user = new User(username, email, password);

        User savedUser = userRepository.save(user);

        return new SingUpResponseDto(savedUser.getId(),savedUser.getUsername(),savedUser.getEmail());
    }

    public UserResponseDto findById(Long id) {

        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT,"Does not exists id : " + id);
        }

        User findUser = optionalUser.get();
        return new UserResponseDto(findUser.getUsername(),findUser.getEmail());
    }
}
