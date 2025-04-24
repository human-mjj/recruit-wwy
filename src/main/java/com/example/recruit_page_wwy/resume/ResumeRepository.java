package com.example.recruit_page_wwy.resume;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ResumeRepository {
    private final EntityManager em;

    public void save(Integer user_id, String title, String exp, String edu, Integer job_id, String location, String qualified, String activity, String img_url) {
        Query query = em.createNativeQuery("insert into resume_tb(user_id,title, exp, edu, job_id, location, qualified, activity, img_url) values (?,?,?,?,?,?,?,?,?)");
        query.setParameter(1, user_id);
        query.setParameter(2, title);
        query.setParameter(3, exp);
        query.setParameter(4, edu);
        query.setParameter(5, job_id);
        query.setParameter(6, location);
        query.setParameter(7, qualified);
        query.setParameter(8, activity);
        query.setParameter(9, img_url);
        query.executeUpdate();
    }

    public List<Resume> findAll(Integer user_id) {
        Query query = em.createNativeQuery("select * from resume_tb where user_id=? order by id desc", Resume.class);
        query.setParameter(1, user_id);
        return query.getResultList();
    }

    public Resume findByResumeId(Integer id) {
        Query query = em.createNativeQuery("select r.ID, r.TITLE,  u.USERNAME,  u.PHONE,  u.EMAIL, r.EXP,  r.EDU, r.JOB_ID,  r.LOCATION,   r.QUALIFIED,   r.ACTIVITY,  r.IMG_URL,  r.LETTER, r.USER_ID from resume_tb r inner join user_tb u on r.id = u.id " +
                "where r.id = ?", Resume.class);
        query.setParameter(1, id);
        return (Resume) query.getSingleResult();
    }

    public void update(Integer id, String title, String exp, String edu, Integer job_id, String location, String qualified, String activity) {
        Query query = em.createNativeQuery("update resume_tb  set TITLE = ?, EXP = ?, EDU = ?, JOB_ID = ?, LOCATION = ?, QUALIFIED = ?, ACTIVITY = ? where r.id = ?", Resume.class);
        query.setParameter(1, id);
        query.setParameter(2, title);
        query.setParameter(3, exp);
        query.setParameter(4, edu);
        query.setParameter(5, job_id);
        query.setParameter(6, location);
        query.setParameter(7, qualified);
        query.setParameter(8, activity);
        query.executeUpdate();
    }
}
