package com.example.recruit_page_wwy.resume;


import com.example.recruit_page_wwy.job.Job;
import com.example.recruit_page_wwy.resumestack.ResumeStack;
import com.example.recruit_page_wwy.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String exp;
    private String edu;

    // 기술 스택 리스트
    @OneToMany(mappedBy = "resume", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ResumeStack> resumeStackList;

    // 희망 직무 번호
    @ManyToOne(fetch = FetchType.LAZY)
    private Job job;

    private String location;
    private String qualified;
    private String activity;
    private String letter;
    private String imgUrl;
}
