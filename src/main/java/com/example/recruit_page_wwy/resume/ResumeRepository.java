package com.example.recruit_page_wwy.resume;


import com.example.recruit_page_wwy.job.Job;
import com.example.recruit_page_wwy.resumestack.ResumeStack;
import com.example.recruit_page_wwy.stack.Stack;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ResumeRepository {
    private final EntityManager em;


    public Resume save(Resume resume) {
        em.persist(resume);
        return resume;
    }

    public void updateStack(Integer resumeId, List<String> resumeStack) {
        for (String skill : resumeStack) {
            Query skillQuery = em.createNativeQuery("insert into resume_stack_tb (resume_id,skill) values (?,?)");
            skillQuery.setParameter(1, resumeId);
            skillQuery.setParameter(2, skill);
            skillQuery.executeUpdate();
        }
    }

    public List<Resume> findAll(Integer user_id, Integer page) {
        Query query = em.createNativeQuery("select * from resume_tb where user_id=? order by id desc", Resume.class);
        query.setParameter(1, user_id);
        query.setFirstResult(page * 5);
        query.setMaxResults(5);
        return query.getResultList();
    }

    public Long totalCount(int userId) {
        Query query = em.createNativeQuery("select count(*) from resume_tb r where r.user_id=?");
        query.setParameter(1, userId);
        Object result = query.getSingleResult();
        return ((Number) result).longValue();
    }

    public Resume findByResumeId(Integer resumeId) {
        return em.find(Resume.class, resumeId);
    }

    public List<Resume> findByUserId(Integer userId) { // employment에서 이력서 작성자 찾기
        String sql = "select * from resume_tb where user_id = ?";
        Query query = em.createNativeQuery(sql, Resume.class);
        query.setParameter(1, userId);
        return query.getResultList();
    }

    public void update(Integer id, String title, String exp, String edu, Integer job_id, String location, String qualified, String activity, List<String> resumeStack, String img_url, String letter) {
        Query query = em.createNativeQuery("update resume_tb  set TITLE = ?, EXP = ?, EDU = ?, JOB_ID = ?, LOCATION = ?, QUALIFIED = ?, ACTIVITY = ?, img_url = ?, letter = ? where id = ?", Resume.class);
        query.setParameter(1, title);
        query.setParameter(2, exp);
        query.setParameter(3, edu);
        query.setParameter(4, job_id);
        query.setParameter(5, location);
        query.setParameter(6, qualified);
        query.setParameter(7, activity);
        query.setParameter(8, id);
        query.setParameter(9, img_url);
        query.setParameter(10, letter);
        query.executeUpdate();

        Query querydelete = em.createNativeQuery("delete from resume_stack_tb where resume_id =? ");
        querydelete.setParameter(1, id);
        querydelete.executeUpdate();

        for (String skill : resumeStack) {
            Query skillQuery = em.createNativeQuery("insert into resume_stack_tb (resume_id, skill) values (?, ?)");
            skillQuery.setParameter(1, id);
            skillQuery.setParameter(2, skill);
            skillQuery.executeUpdate();
        }
    }

    public List<Job> findAllJobs() {
        return em.createQuery("select j from Job j", Job.class).getResultList();
    }

    public List<Stack> findAllStacks() {
        return em.createQuery("select s from Stack s", Stack.class).getResultList();
    }

    public List<ResumeStack> findAllStacksByResumeId(Integer resumeId) {
        return em.createQuery("select rs from ResumeStack rs where rs.resume.id = :resumeId", ResumeStack.class)
                .setParameter("resumeId", resumeId)
                .getResultList();
    }

    public void delete(int resumeId) {
        em.remove(em.find(Resume.class, resumeId));
    }
}
