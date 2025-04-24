package com.example.recruit_page_wwy.employment;


import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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

    public Employment findByIdWithJoins(Integer userId) {
        String sql = """
                    SELECT e.*
                    FROM employment_tb e
                    JOIN user_tb u ON e.user_id = u.id
                    JOIN job_tb j ON e.job_id = j.id
                    WHERE e.id = ?
                """;

        try {
            return (Employment) em.createNativeQuery(sql, Employment.class)
                    .setParameter(1, id)
                    .getSingleResult();
        } catch (RuntimeException e) {
            return null;
        }
    }
}
