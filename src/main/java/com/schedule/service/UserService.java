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
        // Username 선언
        User createdSavedUser = userRepository.save(user);

        return new CreateUserRequestDto(createdSavedUser.getUsername(), createdSavedUser.getEmail());
        // Dto 값에 username, email 담아서 반환
    }


    public SingUpResponseDto signUp(String username, String email,String password) {
        // String 형태로 username,email,password가 Dto에 담겨 존재
        User user = new User(username, email, password);

        User savedUser = userRepository.save(user);
        // userRepository에 save 메서드 실행
        return new SingUpResponseDto(savedUser.getId(),savedUser.getUsername(),savedUser.getEmail());
    }
    // Dto 형태로 id, name, email 값 반환

    public UserResponseDto findById(Long id) {
    // Long id값 findById
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT,"Does not exists id : " + id);
        }
        // optionalUser값이 empty 면 no_content 반환
        User findUser = optionalUser.get();
        return new UserResponseDto(findUser.getId(), findUser.getUsername(),findUser.getEmail());
    }
    @Transactional
    /**
     * 트랜잭션을 관리해주는 어노테이션
     * 하나의 작업 단위를 묶어서 처리하고, 중간에 실패하면 전체를 롤백
     * 즉, 모두 성공하거나, 전부 실패하게 만듬.
     */
    public void userUpdate(Long id, String username, String email) {

        User findUser = userRepository.findByIdOrElseThrow(id);

        findUser.updateUsername(username);

        findUser.updateEmail(email);


    }

    public void delete(Long id) {
        // long id 값 삭제
        User findUser = userRepository.findByIdOrElseThrow(id);

        userRepository.delete(findUser);
    }
}
