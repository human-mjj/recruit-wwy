package com.example.recruit_page_wwy.employstack;


import com.example.recruit_page_wwy.employment.Employment;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name = "employ_stack_tb")
@Entity
public class EmployStack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // 채용 공고 번호
    @ManyToOne(fetch = FetchType.LAZY)
    private Employment employment;

    // 채용 공고에서 사용하는 기술 스택
    private String skill;

    @Builder
    public EmployStack(int id, Employment employment, String skill) {
        this.id = id;
        this.employment = employment;
        this.skill = skill;
    }
}
