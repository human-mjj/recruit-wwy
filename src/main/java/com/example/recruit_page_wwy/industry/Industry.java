package com.example.recruit_page_wwy.industry;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name = "industry_tb")
@Entity
public class Industry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @Builder
    public Industry(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
