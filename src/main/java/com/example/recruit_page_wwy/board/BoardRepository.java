package com.example.recruit_page_wwy.board;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    public Board save(Board board) {
        em.persist(board);

        return board;
    }

    public Long totalCount(String keyword) {
        String sql;
        if (keyword.isBlank()) {
            sql = "select count(b) from Board b";
        } else {
            sql = "select count(b) from Board b where b.title like :keyword";
        }

        Query query = em.createQuery(sql, Long.class);

        if (!keyword.isBlank()) {
            query.setParameter("keyword", "%" + keyword + "%");
        }

        return (Long) query.getSingleResult();
    }

    public List<Board> findAll(int page, String keyword) {
        String sql;
        if (keyword.isBlank()) {
            sql = "select b from Board b order by b.id desc";
        } else {
            sql = "select b from Board b where b.title like :keyword order by b.id desc";
        }
        Query query = em.createQuery(sql, Board.class);

        if (!keyword.isBlank()) {
            query.setParameter("keyword", "%" + keyword + "%");
        }

        query.setFirstResult(page * 5);
        query.setMaxResults(5);

        return query.getResultList();
    }
//    public List<Board> findAll() {
//        Query query = em.createNativeQuery("select * from board_tb order by id desc", Board.class);
//        return query.getResultList();
//    }

//    public Board emailConvertId(Integer id) { //이메일 아이디 조회 쿼리 만들었는데 필요하면 사용
//        Query query = em.createNativeQuery("SELECT id, SUBSTRING(email, 1, LOCATE('@', email) - 1) as emailId FROM user_tb where id = ?;");
//        query.setParameter(1, id);
//        return (Board) query.getSingleResult();
//    }

    public Board findById(Integer id) {
        return em.find(Board.class, id);
    }


    public Board boardUpdate(Integer id, String title, String content) {
        Query query = em.createNativeQuery("update board_tb set title = ?, content = ? where id = ?");
        query.setParameter(1, title);
        query.setParameter(2, content);
        query.setParameter(3, id);
        query.executeUpdate();
        return em.find(Board.class, id);
    }

    public void delete(Integer id) {
        Board board = em.find(Board.class, id);
        em.remove(board);
    }
}
