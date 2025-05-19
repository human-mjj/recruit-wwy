package com.example.recruit_page_wwy.user;


import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

    public User saveUser(User user) {
        em.persist(user);
        return user;
    }

    public User saveCom(User user) {
        em.persist(user);
        return user;
    }


    public User findByEmail(String email) {
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }


    public User findById(Integer sessionId) {
        return em.find(User.class, sessionId);
    }

    public User findUserById(Integer id) { // employment에서 유저 정보찾기
        return em.find(User.class, id);
    }
}
