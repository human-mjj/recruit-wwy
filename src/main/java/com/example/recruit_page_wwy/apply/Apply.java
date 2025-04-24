package com.example.recruit_page_wwy.apply;


import com.example.recruit_page_wwy.employment.Employment;
import com.example.recruit_page_wwy.resume.Resume;
import com.example.recruit_page_wwy.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employment employment;

    @CreationTimestamp
    private Timestamp createdAt;
    private String progress;

    @Builder
    public Apply(int id, User user, Resume resume, Employment employment, Timestamp createdAt, String progress) {
        this.id = id;
        this.user = user;
        this.resume = resume;
        this.employment = employment;
        this.createdAt = createdAt;
        this.progress = progress;
    }
}
