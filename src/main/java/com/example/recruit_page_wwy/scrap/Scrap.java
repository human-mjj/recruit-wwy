package com.example.recruit_page_wwy.scrap;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name = "scrap_tb")
@Entity
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;

    // 1. employmentId == null when resumeId != null
    // 2. resumeId == null when employmentId != null
    private Integer resumeId;
    private Integer employmentId;
}
