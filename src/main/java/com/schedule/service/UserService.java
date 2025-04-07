package com.schedule.service;

import com.schedule.dto.CreateUserRequestDto;
import com.schedule.dto.SingUpResponseDto;
import com.schedule.dto.UserResponseDto;
import com.schedule.entity.User;
import com.schedule.repository.ScheduleRepository;
import com.schedule.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor // Lombok으로 스프링에서 의존성 주입의 방법중 하나 , 생성자 주입 없이 자동으로 설정해주는 어노테이션
public class UserService {

    private final UserRepository userRepository;
    // userRepository 를 private final 형태로 선언
    private final ScheduleRepository scheduleRepository;
    // scheduleRepository를 private final 형태로 선언
    public CreateUserRequestDto save(String username, String email) {
        // Dto 형태로 username , email를 저장
        User user = new User(username, email);
        User createdSavedUser = userRepository.save(user);

        return new CreateUserRequestDto(createdSavedUser.getUsername(), createdSavedUser.getEmail());
    }


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
        return new UserResponseDto(findUser.getId(), findUser.getUsername(),findUser.getEmail());
    }
    @Transactional
    public void userUpdate(Long id, String username, String email) {

        User findUser = userRepository.findByIdOrElseThrow(id);

        findUser.updateUsername(username);

        findUser.updateEmail(email);


    }

    public void delete(Long id) {

        User findUser = userRepository.findByIdOrElseThrow(id);

        userRepository.delete(findUser);
    }
}
