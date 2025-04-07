package com.schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    // 값을 포함해야한다. 값이 없을 땐 false 값이 나옴
    private String username;

    @Column
    private String email;

    @Column(nullable = false)
    private String password;

    // String 타입으로 선언




    public User(String username, String email) {
    }


    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = null;
    }
    // 생성자 생성

    public User() {

    }

    public void updateUsername(String username) {
        this.username = username;
    }
    // username에 updateUsername을 선언

    public void updateEmail(String email) {
        this.email = email;
    }
    // updateEmail을 선언

}

