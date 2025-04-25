package com.example.recruit_page_wwy.apply;


import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ApplyRepository {
    private final EntityManager em;

    public void save(Apply apply) {
        em.persist(apply);
    }
}
