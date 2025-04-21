package com.example.recruit_page_wwy.user;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "user_tb")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String email;
    private String phone;
    private String password;
    private String imgUrl;

    // 기업, 구직자 구분 : not null
    private int role;

    // 기업용 : 구직자는 null 처리해야 함
    private String comName;
    private Integer industryId;
}
