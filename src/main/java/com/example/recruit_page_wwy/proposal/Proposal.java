package com.example.recruit_page_wwy.proposal;


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
@Table(name = "proposal_tb")
@Entity
public class Proposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employment employment;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Proposal(int id, User user, Resume resume, Employment employment, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.resume = resume;
        this.employment = employment;
        this.createdAt = createdAt;
    }
}
