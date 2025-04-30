package com.example.recruit_page_wwy.apply;


import com.example.recruit_page_wwy.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
    public ApplyResponse.UserApplyListDTO findUserApplyById(int userId, User sessionUser) {
        String sql = """
                SELECT DISTINCT u.com_name, j.name, FORMATDATETIME(a.created_at, 'yyyy-MM-dd'), a.progress
                FROM APPLY_TB a
                INNER JOIN EMPLOYMENT_TB e ON a.EMPLOYMENT_ID = e.id
                INNER JOIN RESUME_TB r ON a.RESUME_ID  = r.id
                INNER JOIN user_tb u ON e.user_id=u.id
                
                INNER JOIN JOB_TB j ON e.job_id=j.id
                where a.user_id = ?
                """;
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, userId);

        List<Object[]> applyList = query.getResultList();
        List<ApplyResponse.UserApplyDTO> result = new ArrayList<>();

        for (Object[] row : applyList) {
            String comName = (String) row[0];
            String name = (String) row[1];
            String createdAt = (String) row[2];
            String progress = (String) row[3];
            result.add(new ApplyResponse.UserApplyDTO(comName, name, createdAt, progress));
        }


        return new ApplyResponse.UserApplyListDTO(sessionUser, result);
    }

    // ComApply
    public ApplyResponse.ComApplyListDTO findComApplyById(int userId, User sessionUser) {
        String sql = """
                SELECT a.id, e.title, u.username, j.name, FORMATDATETIME(a.created_at, 'yyyy-MM-dd'), a.PROGRESS
                FROM APPLY_TB a
                INNER JOIN EMPLOYMENT_TB e ON a.EMPLOYMENT_ID = e.id
                INNER JOIN RESUME_TB r ON a.RESUME_ID  = r.id
                INNER JOIN user_tb u ON r.user_id=u.id
                INNER JOIN JOB_TB j ON e.job_id=j.id
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
            String name = (String) row[3];
            String createdAt = (String) row[4];
            String progress = (String) row[5];
            result.add(new ApplyResponse.ComApplyDTO(ApplyId, title, username, name, createdAt, progress));
        }
        return new ApplyResponse.ComApplyListDTO(sessionUser, result);
    }

    public void update(Integer applyId, String progress) {
        em.createNativeQuery("update APPLY_TB set progress = ? where id = ?")
                .setParameter(1, progress)
                .setParameter(2, applyId)
                .executeUpdate();
    }
}
