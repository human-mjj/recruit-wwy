package com.example.recruit_page_wwy.stack;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name = "stack_tb")
@Entity
public class Stack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String skill;
}
