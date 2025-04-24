package com.example.recruit_page_wwy.resume;


import com.example.recruit_page_wwy.job.Job;
import com.example.recruit_page_wwy.resumestack.ResumeStack;
import com.example.recruit_page_wwy.user.User;
import jakarta.persistence.*;
import lombok.Builder;
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

    @Builder
    public Resume(int id, String title, User user, String exp, String edu, List<ResumeStack> resumeStackList, Job job, String location, String qualified, String activity, String letter, String imgUrl) {
        this.id = id;
        this.title = title;
        this.user = user;
        this.exp = exp;
        this.edu = edu;
        this.resumeStackList = resumeStackList;
        this.job = job;
        this.location = location;
        this.qualified = qualified;
        this.activity = activity;
        this.letter = letter;
        this.imgUrl = imgUrl;
    }
}
