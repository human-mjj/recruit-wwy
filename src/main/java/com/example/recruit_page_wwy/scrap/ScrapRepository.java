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

    public List<ScrapRequest.UserScrapDTO> findAllUserScrapById(int id) {
        String sql = """
                SELECT e.title, u.com_name, e.exp, e.location, j.name
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
            result.add(new ScrapRequest.UserScrapDTO(title, comName, exp, location, name));
        }
        return result;
    }
}
