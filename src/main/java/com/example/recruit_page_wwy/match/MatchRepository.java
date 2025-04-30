package com.example.recruit_page_wwy.match;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class MatchRepository {
    private final EntityManager em;

    public List<MatchResponse.EmploymentDTO> findAllRecommendedEmployments(int userId) {
        String sql = """
                SELECT DISTINCT e.id, e.title, u.com_name, e.exp, e.location
                FROM employment_tb e
                JOIN user_tb u ON e.user_id = u.id
                WHERE e.job_id IN (
                        SELECT r.job_id
                        FROM resume_tb r
                        WHERE r.user_id = ?
                    )
                    OR e.id IN (
                        SELECT est.employment_id
                        FROM employ_stack_tb est
                        WHERE est.skill IN (
                            SELECT rst.skill
                            FROM resume_tb r
                            JOIN resume_stack_tb rst ON r.id = rst.resume_id
                            WHERE r.user_id = ?
                        )
                    )
                """;

        Query query = em.createNativeQuery(sql);
        query.setParameter(1, userId);
        query.setParameter(2, userId);

        List<Object[]> resultList = query.getResultList();
        List<MatchResponse.EmploymentDTO> result = new ArrayList<>();

        for (Object[] row : resultList) {
            Integer employmentId = ((Number) row[0]).intValue();
            String title = (String) row[1];
            String comName = (String) row[2];
            String exp = (String) row[3];
            String location = (String) row[4];

            result.add(new MatchResponse.EmploymentDTO(employmentId, title, comName, exp, location));
        }

        return result;
    }

    public List<MatchResponse.ResumeDTO> findAllRecommendedResumes(int userId) {
        String sql = """
                SELECT DISTINCT r.id, r.title, u.username
                FROM resume_tb r
                JOIN resume_stack_tb rst ON r.id = rst.resume_id
                JOIN user_tb u ON r.user_id = u.id
                WHERE (r.job_id IN (
                           SELECT e.job_id
                           FROM employment_tb e
                           WHERE e.user_id = ?
                       )
                   OR rst.skill IN (
                           SELECT est.skill
                           FROM employment_tb e
                           JOIN employ_stack_tb est ON e.id = est.employment_id
                           WHERE e.user_id = ?
                       ))
                """;

        Query query = em.createNativeQuery(sql);
        query.setParameter(1, userId);
        query.setParameter(2, userId);

        List<Object[]> resultList = query.getResultList();
        List<MatchResponse.ResumeDTO> result = new ArrayList<>();

        for (Object[] row : resultList) {
            Integer resumeId = ((Number) row[0]).intValue();
            String title = (String) row[1];
            String author = (String) row[2];

            result.add(new MatchResponse.ResumeDTO(resumeId, title, author));
        }

        return result;
    }

    public List<MatchResponse.EmploymentDTO> findAllProposals(int userId) {
        String sql = """
                select distinct e.id, e.title, eu.com_name, e.exp, e.location
                from employment_tb e
                join proposal_tb p on p.employment_id = e.id
                join user_tb eu on eu.id = e.user_id
                join resume_tb r on p.resume_id = r.id
                join user_tb u on r.user_id = u.id
                where u.id = ?
                """;
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, userId);
        List<Object[]> resultList = query.getResultList();
        List<MatchResponse.EmploymentDTO> result = new ArrayList<>();

        for (Object[] row : resultList) {
            Integer employmentId = ((Number) row[0]).intValue();
            String title = (String) row[1];
            String comName = (String) row[2];
            String exp = (String) row[3];
            String location = (String) row[4];
            result.add(new MatchResponse.EmploymentDTO(employmentId, title, comName, exp, location));
        }
        return result;
    }
}
