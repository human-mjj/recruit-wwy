package com.example.recruit_page_wwy.user;


import com.example.recruit_page_wwy.industry.Industry;
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
    @ManyToOne(fetch = FetchType.LAZY)
    private Industry industry;

    @Builder
    private User(int id, String username, String email, String phone, String password, String imgUrl, int role, String comName, Industry industry) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.imgUrl = null;
        this.role = role;
        this.phone = phone;
        this.comName = comName;
        this.industry = industry;
    }

}
