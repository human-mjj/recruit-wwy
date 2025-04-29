package com.example.recruit_page_wwy.resume;


import com.example.recruit_page_wwy.apply.Apply;
import com.example.recruit_page_wwy.job.Job;
import com.example.recruit_page_wwy.proposal.Proposal;
import com.example.recruit_page_wwy.resumestack.ResumeStack;
import com.example.recruit_page_wwy.scrap.Scrap;
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
    @OneToMany(mappedBy = "resume", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ResumeStack> resumeStackList;

    // 지원 리스트
    @OneToMany(mappedBy = "resume", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Apply> applyList;

    // 제안 리스트
    @OneToMany(mappedBy = "resume", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Proposal> proposalList;

    // 스크랩 리스트
    @OneToMany(mappedBy = "resume", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Scrap> scrapList;

    // 희망 직무 번호
    @ManyToOne(fetch = FetchType.LAZY)
    private Job job;

    private String location;
    private String qualified;
    private String activity;
    private String letter;
    private String imgUrl;
}
