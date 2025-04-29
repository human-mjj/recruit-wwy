package com.example.recruit_page_wwy.scrap;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ScrapRepository {
    private final EntityManager em;

    public Long userScrapTotalCount(int userId) {
        String sql = """
                SELECT COUNT(*)
                FROM scrap_tb s
                WHERE s.user_id = ?
                """;
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, userId);
        Object result = query.getSingleResult();
        return ((Number) result).longValue();
    }

    public Long comScrapTotalCount(int userId) {
        String sql = """
                SELECT COUNT(*)
                FROM scrap_tb s
                WHERE s.user_id = ?
                """;
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, userId);
        Object result = query.getSingleResult();
        return ((Number) result).longValue();
    }

    public Scrap findByUserIdAndEmployId(Integer sessionUserId, Integer employmentId) {
        Query query = em.createQuery("select s from Scrap s where s.user.id = :sessionUserId and s.employment.id = :employmentId", Scrap.class);
        query.setParameter("sessionUserId", sessionUserId);
        query.setParameter("employmentId", employmentId);
        try {
            return (Scrap) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<ScrapRequest.UserScrapDTO> findAllUserScrapById(int userId, int page, int size) {
        String sql = """
                SELECT e.title, u.com_name, e.exp, e.location, j.name
                FROM SCRAP_TB s
                INNER JOIN EMPLOYMENT_TB e ON s.EMPLOYMENT_ID = e.id
                INNER JOIN USER_TB u ON e.USER_ID = u.id
                INNER JOIN job_tb j On e.job_id = j.id
                WHERE s.user_id = ?
                LIMIT ? OFFSET ?
                """;
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, userId);
        query.setParameter(2, size);
        query.setParameter(3, page * size);

        List<Object[]> scrapList = query.getResultList();
        List<ScrapRequest.UserScrapDTO> result = new ArrayList<>();
        for (Object[] row : scrapList) {
            String title = (String) row[0];
            String comName = (String) row[1];
            String exp = (String) row[2];
            String location = (String) row[3];
            String name = (String) row[4];
            result.add(new ScrapRequest.UserScrapDTO(title, comName, exp, location, name));
        }
        return result;
    }

    public List<ScrapRequest.ComScrapDTO> findAllComScrapById(int userId, int page, int size) {
        String sql = """
                SELECT r.title, u.username
                FROM SCRAP_TB s
                INNER JOIN RESUME_TB r ON s.RESUME_ID = r.ID
                INNER JOIN USER_TB u ON r.USER_ID = u.id
                WHERE s.user_id = ?
                LIMIT ? OFFSET ?
                """;
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, userId);
        query.setParameter(2, size);
        query.setParameter(3, page * size);

        List<Object[]> scrapList = query.getResultList();
        List<ScrapRequest.ComScrapDTO> result = new ArrayList<>();
        for (Object[] row : scrapList) {
            String title = (String) row[0];
            String username = (String) row[1];
            result.add(new ScrapRequest.ComScrapDTO(title, username));
        }
        return result;
    }

    public Scrap save(Scrap scrap) {
        em.persist(scrap);
        return scrap;
    }

    public Scrap findById(Integer id) {
        return em.find(Scrap.class, id);
    }

    public void deleteById(Integer id) {
        Query query = em.createQuery("delete from Scrap s where s.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public Scrap findByUserIdAndResumeId(int id, Integer resumeId) {
        return em.createQuery("select s from Scrap s where s.user.id = :id and s.resume.id = :resumeId", Scrap.class)
                .setParameter("id", id)
                .setParameter("resumeId", resumeId)
                .getSingleResult();
    }
}
