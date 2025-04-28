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

    public Scrap findByUserIdAndresumeId(Integer sessionUserId, Integer resumeId) {
        Query query = em.createQuery("select s from Scrap s where s.user.id = :sessionUserId and s.resume.id = :resumeId", Scrap.class);
        query.setParameter("sessionUserId", sessionUserId);
        query.setParameter("resumeId", resumeId);
        try {
            return (Scrap) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<ScrapRequest.UserScrapDTO> findAllUserScrapById(int id) {
        String sql = """
                SELECT e.title, u.com_name, e.exp, e.location, j.name, e.id
                FROM SCRAP_TB s
                INNER JOIN EMPLOYMENT_TB e ON s.EMPLOYMENT_ID = e.id
                INNER JOIN USER_TB u ON e.USER_ID = u.id
                INNER JOIN job_tb j On e.job_id = j.id
                where s.user_id = ?;
                """;
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, id);

        List<Object[]> scrapList = query.getResultList();
        List<ScrapRequest.UserScrapDTO> result = new ArrayList<>();
        for (Object[] row : scrapList) {
            String title = (String) row[0];
            String comName = (String) row[1];
            String exp = (String) row[2];
            String location = (String) row[3];
            String name = (String) row[4];
            Integer emplorymentId = (Integer) row[5];
            result.add(new ScrapRequest.UserScrapDTO(title, comName, exp, location, name, emplorymentId));
        }
        return result;
    }

    public List<ScrapRequest.ComScrapDTO> findAllComScrapById(int userid) {
        String sql = """
                SELECT r.id, r.title, u.username 
                FROM SCRAP_TB s
                INNER JOIN RESUME_TB r ON S.RESUME_ID = R.ID
                INNER JOIN USER_TB u ON s.RESUME_ID = u.id
                where s.user_id = ?
                """;
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, userid);

        List<Object[]> scrapList = query.getResultList();
        List<ScrapRequest.ComScrapDTO> result = new ArrayList<>();
        for (Object[] row : scrapList) {
            Integer id = (Integer) row[0];
            String title = (String) row[1];
            String username = (String) row[2];
            result.add(new ScrapRequest.ComScrapDTO(id, title, username));
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

    public void deleteByUserId(Integer id) {
        Query query = em.createQuery("delete from Scrap s where s.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public void deleteByComId(Integer id) {
        Query query = em.createQuery("delete from Scrap s where s.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
