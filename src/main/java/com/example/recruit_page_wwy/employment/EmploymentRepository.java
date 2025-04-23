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

    public List<Employment> findByUserId(Integer userId) {
        Query query = em.createQuery(
                "SELECT e.title, u.comName, e.exp, e.location, j.name, e.imgUrl " +
                        "FROM Employment e " +
                        "JOIN e.user u " +
                        "JOIN e.job j " +
                        "WHERE e.user.id = :userId");
        query.setParameter("userId", userId);
        return query.getResultList();
    }
}
