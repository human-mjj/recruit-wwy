package com.example.recruit_page_wwy.employment;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class EmploymentRepository {
    private final EntityManager em;

    public List<Employment> findAllByUserId(Integer userId) {
        String jpql = """
                    SELECT e FROM Employment e
                    JOIN FETCH e.user
                    JOIN FETCH e.job
                    WHERE e.user.id = :userId
                    ORDER BY e.id DESC
                """;
        return em.createQuery(jpql, Employment.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public List<Employment> findAll() {
        String jpql = """
                    SELECT e FROM Employment e 
                    JOIN FETCH e.user
                    JOIN FETCH e.job
                    ORDER BY e.id DESC
                """;
        return em.createQuery(jpql, Employment.class)
                .getResultList();
    }
    public List<Employment> findTop4ByOrderByIdDesc() {
        String jpql = """
            SELECT e FROM Employment e
            JOIN FETCH e.user
            JOIN FETCH e.job
            ORDER BY e.id DESC
        """;
        return em.createQuery(jpql, Employment.class)
                .setMaxResults(4)
                .getResultList();
    }
}
