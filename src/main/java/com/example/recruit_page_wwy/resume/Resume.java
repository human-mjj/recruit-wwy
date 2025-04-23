package com.example.recruit_page_wwy.resume;


import com.example.recruit_page_wwy.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name = "resume_tb")
@Entity

public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;

    // 작성자 ID
    @Column(name = "user_id")  //
    private int userId;

    private String exp;
    private String edu;

    // 희망 직무 번호
    private int jobId;

    private String location;
    private String qualified;
    private String activity;
    private String letter;
    private String imgUrl;

    @Transient
    private User user;  // DB에 저장되지 않고 JPA가 관리하지 않음


}
