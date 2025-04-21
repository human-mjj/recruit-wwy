package com.example.recruit_page_wwy.reply;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Table(name = "reply_tb")
@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int boardId;
    private int userId;
    private String content;
    private Timestamp createdAt;
}
