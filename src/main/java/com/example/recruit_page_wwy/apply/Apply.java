package com.example.recruit_page_wwy.apply;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Table(name = "apply_tb")
@Entity
public class Apply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // user가 조회할 때만 사용
    private int userId;
    private int resumeId;
    private int employmentId;
    private Timestamp createdAt;
    private String progress;
}
