package com.example.recruit_page_wwy.resumestack;


import com.example.recruit_page_wwy.resume.Resume;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name = "resume_stack_tb")
@Entity
public class ResumeStack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // 이력서 ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id", nullable = false, foreignKey = @ForeignKey(name = "fk_resume_stack_to_resume"))
    private Resume resume;

    // 이력서에 작성할 기술 스택
    private String skill;
}
