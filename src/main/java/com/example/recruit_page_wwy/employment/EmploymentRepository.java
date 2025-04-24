package com.example.recruit_page_wwy.employment;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
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

    public Object findDetailRawById(Integer id) {
        String sql = """
        SELECT 
            e.id,
            u.img_url,
            e.title,
            u.com_name,
            e.exp,
            e.edu,
            e.shift,
            e.sal,
            e.working_time,
            e.location,
            e.end_date,
            j.name,
            e.duty,
            e.qualification
        FROM employment_tb e
        JOIN user_tb u ON e.user_id = u.id
        JOIN job_tb j ON e.job_id = j.id
        WHERE e.id = ?
    """;

        Query query = em.createNativeQuery(sql);
        query.setParameter(1, id);
        return query.getSingleResult();
    }

    public List<String> findStackByEmploymentId(Integer id) {
        String sql = "SELECT skill FROM employ_stack_tb WHERE employment_id = ?";
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, id);
        return query.getResultList();
    }

    public boolean isOwner(Integer employmentId, Integer userId) {
        String sql = "SELECT COUNT(*) FROM employment_tb WHERE id = ? AND user_id = ?";
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, employmentId);
        query.setParameter(2, userId);

        Long count = ((Number) query.getSingleResult()).longValue();
        return count > 0;
    }
}
