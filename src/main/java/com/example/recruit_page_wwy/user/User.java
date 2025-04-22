package com.example.recruit_page_wwy.user;


import jakarta.persistence.*;
import lombok.Builder;
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


    @Builder
    private User(int id, String username, String email, String phone, String password, String imgUrl, int role, String comName, Integer industryId) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.imgUrl = null;
        this.role = role;
        this.comName = comName;
        this.industryId = industryId;
    }

}
