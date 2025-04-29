package com.example.recruit_page_wwy.employment;


import com.example.recruit_page_wwy.employstack.EmployStack;
import com.example.recruit_page_wwy.job.Job;
import com.example.recruit_page_wwy.stack.Stack;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class EmploymentRepository {
    private final EntityManager em;

    public Long totalCount(Integer userId) {
        String jpql = """
                SELECT COUNT(e) 
                FROM Employment e 
                WHERE e.user.id = :userId
                """;
        return em.createQuery(jpql, Long.class)
                .setParameter("userId", userId)
                .getSingleResult();
    }

    public List<Employment> findAllByUserId(Integer userId, Integer page) {
        int size = 8;
        int start = (page - 1) * size;
        String jpql = """
                    SELECT e FROM Employment e
                    JOIN FETCH e.user
                    JOIN FETCH e.job
                    WHERE e.user.id = :userId
                    ORDER BY e.id DESC
                """;
        return em.createQuery(jpql, Employment.class)
                .setParameter("userId", userId)
                .setFirstResult(start)
                .setMaxResults(size)
                .getResultList();
    }

    public Long totalCount() {
        String jpql = "SELECT COUNT(e) FROM Employment e";
        Query query = em.createQuery(jpql, Long.class);
        return (Long) query.getSingleResult();
    }

    public List<Employment> findAll(int page) {
        String jpql = """
                    SELECT e FROM Employment e 
                    JOIN FETCH e.user
                    JOIN FETCH e.job
                    ORDER BY function('RAND')
                """;
        Query query = em.createQuery(jpql, Employment.class);
        query.setFirstResult(page * 16);
        query.setMaxResults(16);

        return query.getResultList();
    }

    public List<Employment> findTop4ByOrderByIdDesc() {
        String jpql = """
                    SELECT e FROM Employment e
                    JOIN FETCH e.user
                    JOIN FETCH e.job
                    ORDER BY e.endDate DESC
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

    public Employment findByEmploymentId(int employmentId) {
        return em.find(Employment.class, employmentId);
    }

    public List<Job> findAllJobs() {
        return em.createQuery("select j from Job j", Job.class).getResultList();
    }

    public List<Stack> findAllStacks() {
        return em.createQuery("select s from Stack s", Stack.class).getResultList();
    }

    public void save(Employment savingEmployment, List<String> stackList) {
        em.persist(savingEmployment);
        em.flush(); // 여기서 DB에 insert 실행되고 id가 채워짐
        int employmentId = savingEmployment.getId(); // 바로 id 꺼내기

        em.createNativeQuery("delete from employ_stack_tb where employment_id = ?")
                .setParameter(1, employmentId)
                .executeUpdate();

        for (String s : stackList) {
            em.createNativeQuery("insert into employ_stack_tb(employment_id, skill) values(?, ?)")
                    .setParameter(1, employmentId)
                    .setParameter(2, s)
                    .executeUpdate();
        }
    }

    public List<EmployStack> findAllStacksByEmploymentId(int employmentId) {
        return em.createQuery("select es from EmployStack es where es.employment.id = :employmentId", EmployStack.class)
                .setParameter("employmentId", employmentId)
                .getResultList();
    }

    public void updateStack(int employmentId, List<String> stackList) {
        em.createNativeQuery("delete from employ_stack_tb where employment_id = ?")
                .setParameter(1, employmentId)
                .executeUpdate();

        for (String s : stackList) {
            em.createNativeQuery("insert into employ_stack_tb(employment_id, skill) values(?, ?)")
                    .setParameter(1, employmentId)
                    .setParameter(2, s)
                    .executeUpdate();
        }
    }
}
