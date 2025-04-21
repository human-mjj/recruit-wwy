package com.example.recruit_page_wwy.reply;


import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ReplyRepository {
    private final EntityManager em;

}
