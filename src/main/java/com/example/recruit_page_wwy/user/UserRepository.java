package com.example.recruit_page_wwy.user;


import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

    public void saveUser(User user) {
        em.persist(user);
    }

    public void saveCom(User user) {
        em.persist(user);
    }


    public User findByEmailAndPassword(String email, String password) {
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password= :password", User.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public User findById(Integer sessionId) {
        return em.find(User.class, sessionId);
    }
}
