package com.example.recruit_page_wwy.resume;


import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ResumeRepository {
    private final EntityManager em;

}
