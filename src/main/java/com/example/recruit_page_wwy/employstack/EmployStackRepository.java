package com.example.recruit_page_wwy.employstack;


import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class EmployStackRepository {
    private final EntityManager em;

    public void save(EmployStack employStack) {
        em.persist(employStack);
    }

    public void deleteByEmploymentId(int employmentId) {

    }
}
