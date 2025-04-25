package com.example.recruit_page_wwy.apply;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ApplyRepository {
    private final EntityManager em;

    public void save(Apply apply) {
        em.persist(apply);
    }

    // UserApply
    public List<ApplyResponse.UserApplyDTO> findUserApplyById(int userId) {
        String sql = """
                SELECT DISTINCT u.com_name, e.job_id, a.created_at, a.progress
                FROM APPLY_TB a
                INNER JOIN EMPLOYMENT_TB e ON a.EMPLOYMENT_ID = e.id
                INNER JOIN RESUME_TB r ON a.RESUME_ID  = r.id
                INNER JOIN user_tb u ON e.user_id=u.id
                where a.user_id = ?
                """;
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, userId);

        List<Object[]> applyList = query.getResultList();
        List<ApplyResponse.UserApplyDTO> result = new ArrayList<>();

        for (Object[] row : applyList) {
            String comName = (String) row[0];
            Integer jobId = (Integer) row[1];
            Timestamp createdAt = (Timestamp) row[2];
            String progress = (String) row[3];
            result.add(new ApplyResponse.UserApplyDTO(comName, jobId, createdAt, progress));
        }

        return result;
    }

    // ComApply
    public List<ApplyResponse.ComApplyDTO> findComApplyById(int userId) {
        String sql = """
                SELECT a.id, e.title, u.username, e.job_id,  a.created_at, a.PROGRESS
                FROM APPLY_TB a
                INNER JOIN EMPLOYMENT_TB e ON a.EMPLOYMENT_ID = e.id
                INNER JOIN RESUME_TB r ON a.RESUME_ID  = r.id
                INNER JOIN user_tb u ON r.user_id=u.id
                where e.user_id = ?
                """;
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, userId);

        List<Object[]> applyList = query.getResultList();
        List<ApplyResponse.ComApplyDTO> result = new ArrayList<>();

        for (Object[] row : applyList) {
            Integer ApplyId = (Integer) row[0];
            String title = (String) row[1];
            String username = (String) row[2];
            Integer jobId = (Integer) row[3];
            Timestamp createdAt = (Timestamp) row[4];
            String progress = (String) row[5];
            result.add(new ApplyResponse.ComApplyDTO(ApplyId, title, username, jobId, createdAt, progress));
        }

        return result;
    }
}
