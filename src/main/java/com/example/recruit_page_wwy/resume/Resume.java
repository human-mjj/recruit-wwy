package com.example.recruit_page_wwy.resume;


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
}
