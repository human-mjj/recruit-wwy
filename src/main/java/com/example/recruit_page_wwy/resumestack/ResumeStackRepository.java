package com.example.recruit_page_wwy.resumestack;


import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ResumeStackRepository {
    private final EntityManager em;

}
