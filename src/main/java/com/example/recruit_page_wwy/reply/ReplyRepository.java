package com.example.recruit_page_wwy.reply;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ReplyRepository {
    private final EntityManager em;

    public List<Reply> findByBoardId(Integer board_id) {
        Query query = em.createNativeQuery("select from reply_tb where board_id=?", Reply.class);
        query.setParameter(1, board_id);
        return query.getResultList();

    }

    public Reply findById(Integer id) {
        return em.find(Reply.class, id);
    }

    public Reply replySave(Reply reply) {
        em.persist(reply);
        return reply;
    }

    public void deleteById(Integer id) {
        em.createQuery("delete from Reply r where r.id= :id")
                .setParameter("id", id)
                .executeUpdate();

    }
}
