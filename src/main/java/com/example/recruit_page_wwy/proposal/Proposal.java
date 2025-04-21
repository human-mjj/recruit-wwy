package com.example.recruit_page_wwy.proposal;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Table(name = "proposal_tb")
@Entity
public class Proposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private int resumeId;
    private int employmentId;
    private Timestamp createdAt;
}
