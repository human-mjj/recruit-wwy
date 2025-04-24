package com.example.recruit_page_wwy.scrap;


import com.example.recruit_page_wwy.employment.Employment;
import com.example.recruit_page_wwy.resume.Resume;
import com.example.recruit_page_wwy.user.User;
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

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    // 1. employmentId == null when resumeId != null
    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;

    // 2. resumeId == null when employmentId != null
    @ManyToOne(fetch = FetchType.LAZY)
    private Employment employment;
}
