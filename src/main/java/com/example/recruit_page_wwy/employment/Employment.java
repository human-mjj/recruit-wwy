package com.example.recruit_page_wwy.employment;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Table(name = "employment_tb")
@Entity
public class Employment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;

    // 작성자 ID
    private int userId;

    private String exp;
    private String edu;
    private String shift;

    // 직무 번호
    private int jobId;

    private String duty;
    private String qualification;
    private Integer sal;
    private String workingTime;
    private String location;
    private Timestamp endDate;
    private String imgUrl;
}
