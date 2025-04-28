package com.example.recruit_page_wwy.employment;


import com.example.recruit_page_wwy.employstack.EmployStack;
import com.example.recruit_page_wwy.job.Job;
import com.example.recruit_page_wwy.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Builder
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
    @ManyToOne(fetch = FetchType.LAZY)
    private Job job;

    private String duty;
    private String qualification;
    private Integer sal;
    private String workingTime;
    private String location;
    private Date endDate;
    private String imgUrl;

    @Builder
    public Employment(int id, String title, User user, String exp, String edu, String shift, List<EmployStack> employStackList, Job job, String duty, String qualification, Integer sal, String workingTime, String location, Date endDate, String imgUrl) {
        this.id = id;
        this.title = title;
        this.user = user;
        this.exp = exp;
        this.edu = edu;
        this.shift = shift;
        this.employStackList = employStackList;
        this.job = job;
        this.duty = duty;
        this.qualification = qualification;
        this.sal = sal;
        this.workingTime = workingTime;
        this.location = location;
        this.endDate = endDate;
        this.imgUrl = imgUrl;
    }

    public void update(EmploymentRequest.SaveDTO dto) {
        this.title = dto.getTitle();
        this.exp = dto.getExp();
        this.edu = dto.getEdu() + "$" + dto.getSchoolName();
        this.shift = dto.getShift();
        this.sal = dto.getSal();
        this.workingTime = dto.getWorkingTime();
        this.location = dto.getLocation() + " " + dto.getSpecificLocation();
        this.endDate = dto.getEndDate();
        this.duty = String.join("$", dto.getDuty());
        this.qualification = String.join("$", dto.getQualification());
        this.job = Job.builder().id(dto.getJobId()).build(); // 연관관계 수정
    }
}
