package com.example.recruit_page_wwy.employment;


import com.example.recruit_page_wwy.employstack.EmployStack;
import com.example.recruit_page_wwy.job.Job;
import com.example.recruit_page_wwy.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@Getter
@Table(name = "employment_tb")
@Entity
public class Employment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;

    // 작성자 정보
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String exp;
    private String edu;
    private String shift;

    // 기술 스택 리스트
    @OneToMany(mappedBy = "employment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EmployStack> employStackList;

    // 직무
    @OneToOne(fetch = FetchType.LAZY)
    private Job job;

    private String duty;
    private String qualification;
    private Integer sal;
    private String workingTime;
    private String location;
    private Timestamp endDate;
    private String imgUrl;
}
